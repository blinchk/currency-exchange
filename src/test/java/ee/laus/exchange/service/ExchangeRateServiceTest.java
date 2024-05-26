package ee.laus.exchange.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.laus.exchange.client.response.exchange.ExchangeRateApiResponse;
import ee.laus.exchange.client.response.exchange.ExchangeRateListApiResponse;
import ee.laus.exchange.model.currency.Currency;
import ee.laus.exchange.model.currency.request.ConvertCurrencyRequest;
import ee.laus.exchange.model.exchange.ExchangeRate;
import ee.laus.exchange.model.exchange.response.ExchangeRateSummary;
import ee.laus.exchange.repository.CurrencyRepository;
import ee.laus.exchange.repository.ExchangeRateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ExchangeRateServiceTest {
    ExchangeRateService exchangeRateService;
    @Mock
    CurrencyRepository currencyRepository;
    @Mock
    ExchangeRateRepository exchangeRateRepository;
    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        exchangeRateService = new ExchangeRateService(currencyRepository,
                exchangeRateRepository,
                objectMapper);
    }

    @Test
    void upload() {
        ExchangeRateListApiResponse response = new ExchangeRateListApiResponse(Collections.emptyList());
        exchangeRateService.upload(response);
        verify(exchangeRateRepository).saveAll(Collections.emptyList());
    }

    @Test
    void getExchangeRates() {
        String code = "USD";
        LocalDate date = LocalDate.now();
        Double amount = 1.314;
        Currency currency = new Currency(3L, code, "US dollar", 2, "777");
        ExchangeRate exchangeRate = new ExchangeRate(1L, currency, amount, date);
        when(exchangeRateRepository.findAllByCurrencyCode(code)).thenReturn(List.of(exchangeRate));
        ExchangeRateSummary actual = exchangeRateService.getExchangeRates(code);
        assertEquals(date, actual.current().date());
        assertEquals(date, actual.max().date());
        assertEquals(date, actual.min().date());
        assertEquals(amount, actual.current().amount());
        assertEquals(amount, actual.max().amount());
        assertEquals(amount, actual.min().amount());
    }

    @Test
    void convert() {
        double amount = 1.314;
        String code = "USD";
        LocalDate date = LocalDate.now();
        Currency currency = new Currency(3L, code, "US dollar", 2, "777");
        ExchangeRate exchangeRate = new ExchangeRate(1L, currency, amount, date);
        BigDecimal expected = BigDecimal.valueOf(amount).multiply(BigDecimal.valueOf(2));
        ConvertCurrencyRequest request = new ConvertCurrencyRequest(BigDecimal.valueOf(2), code);
        when(exchangeRateRepository.findCurrentByCurrencyCode(code)).thenReturn(Optional.of(exchangeRate));
        BigDecimal actual = exchangeRateService.convert(request);
        verify(exchangeRateRepository).findCurrentByCurrencyCode(code);
        assertEquals(expected, actual);
    }
}