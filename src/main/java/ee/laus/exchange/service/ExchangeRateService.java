package ee.laus.exchange.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.laus.exchange.client.response.exchange.ExchangeRateListApiResponse;
import ee.laus.exchange.exception.EntityNotFoundException;
import ee.laus.exchange.model.currency.request.ConvertCurrencyRequest;
import ee.laus.exchange.model.exchange.ExchangeRate;
import ee.laus.exchange.model.exchange.dto.ExchangeRateDto;
import ee.laus.exchange.model.exchange.response.ExchangeRateListItem;
import ee.laus.exchange.model.exchange.response.ExchangeRateSummary;
import ee.laus.exchange.repository.CurrencyRepository;
import ee.laus.exchange.repository.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeRateService {
    private final CurrencyRepository currencyRepository;
    private final ExchangeRateRepository exchangeRateRepository;
    private final ObjectMapper mapper;

    public void upload(ExchangeRateListApiResponse response) {
        exchangeRateRepository.deleteAll();
        List<ExchangeRate> exchangeRates = response.exchangeRates().stream()
                .map(ExchangeRateDto::of)
                .map(rate -> ExchangeRate.builder()
                        .currency(currencyRepository.findByCode(rate.code())
                                .orElseThrow(() -> new EntityNotFoundException("Currency with code " + rate.code() + " not found")))
                        .amount(rate.amount())
                        .date(rate.date())
                        .build()
                )
                .toList();
        exchangeRateRepository.saveAll(exchangeRates);
    }

    public ExchangeRateSummary getExchangeRates(String code) {
        List<ExchangeRateListItem> rates = exchangeRateRepository.findAllByCurrencyCode(code).stream()
                .map(exchangeRate -> mapper.convertValue(exchangeRate, ExchangeRateListItem.class))
                .sorted(Comparator.comparing(ExchangeRateListItem::date))
                .toList();
        ExchangeRateListItem current = rates.stream().max(Comparator.comparing(ExchangeRateListItem::date)).orElse(null);
        ExchangeRateListItem max = rates.stream().max(Comparator.comparing(ExchangeRateListItem::amount)).orElse(null);
        ExchangeRateListItem min = rates.stream().min(Comparator.comparing(ExchangeRateListItem::amount)).orElse(null);
        return new ExchangeRateSummary(rates, current, max, min);
    }

    public BigDecimal convert(ConvertCurrencyRequest request) {
        if (request.amount() == null || BigDecimal.ZERO.compareTo(request.amount()) > 0) return BigDecimal.ZERO;
        Double amount = exchangeRateRepository.findCurrentByCurrencyCode(request.code())
                .orElseThrow(() -> new EntityNotFoundException("Current exchange rate is not found"))
                .getAmount();
        return BigDecimal.valueOf(amount).multiply(request.amount());
    }
}
