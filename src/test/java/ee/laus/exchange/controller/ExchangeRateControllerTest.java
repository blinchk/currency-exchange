package ee.laus.exchange.controller;

import ee.laus.exchange.model.currency.request.ConvertCurrencyRequest;
import ee.laus.exchange.service.ExchangeRateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.concurrent.Exchanger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ExchangeRateControllerTest {
    ExchangeRateController exchangeRateController;
    @Mock
    ExchangeRateService exchangeRateService;

    @BeforeEach
    void setUp() {
        exchangeRateController = new ExchangeRateController(exchangeRateService);
    }

    @Test
    void getExchangeRates() {
        String code = "USD";
        exchangeRateController.getExchangeRates(code);
        verify(exchangeRateService).getExchangeRates(code);
    }

    @Test
    void convert() {
        String code = "USD";
        ConvertCurrencyRequest request = new ConvertCurrencyRequest(BigDecimal.ONE, code);
        exchangeRateController.convert(request);
        verify(exchangeRateService).convert(request);
    }
}