package ee.laus.exchange.controller;

import ee.laus.exchange.service.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CurrencyControllerTest {
    CurrencyController currencyController;
    @Mock
    CurrencyService currencyService;

    @BeforeEach
    void setUp() {
        currencyController = new CurrencyController(currencyService);
    }

    @Test
    void getCurrencies() {
        String searchTerm = "EUR";
        currencyController.getCurrencies(searchTerm);
        verify(currencyService).getCurrencies(searchTerm);
    }

    @Test
    void getCurrency() {
        String code = "AUD";
        currencyController.getCurrency(code);
        verify(currencyService).getCurrency(code);
    }
}