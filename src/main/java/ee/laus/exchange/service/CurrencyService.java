package ee.laus.exchange.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.laus.exchange.client.currency.CurrencyListApiResponse;
import ee.laus.exchange.model.Currency;
import ee.laus.exchange.response.CurrencyResponse;
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

    public List<CurrencyResponse> getCurrencies() {
        return repository.findAll().stream()
                .map(currency -> mapper.convertValue(currency, CurrencyResponse.class))
                .toList();
    }
}
