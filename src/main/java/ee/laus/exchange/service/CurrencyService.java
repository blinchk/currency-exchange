package ee.laus.exchange.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.laus.exchange.client.response.currency.CurrencyListApiResponse;
import ee.laus.exchange.model.currency.Currency;
import ee.laus.exchange.model.currency.CurrencyResponse;
import ee.laus.exchange.response.CurrencyListItem;
import lombok.RequiredArgsConstructor;
import ee.laus.exchange.repository.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyRepository repository;
    private final ObjectMapper mapper;

    public void upload(CurrencyListApiResponse response) {
        repository.saveAll(
                response.currencies().stream()
                        .map(currency -> Currency.builder()
                                .name(currency.name())
                                .minorUnits(currency.minorUnits())
                                .code(currency.code())
                                .numericCode(currency.numericCode())
                                .build())
                        .toList()
        );
    }

    public List<CurrencyListItem> getCurrencies(String searchTerm) {
        List<Currency> currencies;
        if (searchTerm != null) {
            currencies = repository.findAllBySearchTerm(searchTerm);
        } else {
            currencies = repository.findAllWithExchangeRate();
        }
        return currencies.stream()
                .map(currency -> mapper.convertValue(currency, CurrencyListItem.class))
                .toList();
    }

    public CurrencyResponse getCurrency(String code) {
        return mapper.convertValue(repository.findByCode(code), CurrencyResponse.class);
    }
}
