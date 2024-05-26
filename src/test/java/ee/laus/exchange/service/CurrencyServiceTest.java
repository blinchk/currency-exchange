package ee.laus.exchange.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import ee.laus.exchange.client.response.currency.CurrencyListApiResponse;
import ee.laus.exchange.exception.EntityNotFoundException;
import ee.laus.exchange.model.currency.Currency;
import ee.laus.exchange.repository.CurrencyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CurrencyServiceTest {
    CurrencyService currencyService;
    @Mock
    CurrencyRepository currencyRepository;
    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        currencyService = new CurrencyService(currencyRepository, objectMapper);
    }

    @Test
    void upload() {
        CurrencyListApiResponse response = new CurrencyListApiResponse(Collections.emptyList());
        currencyService.upload(response);
        verify(currencyRepository).saveAll(Collections.emptyList());
    }

    @Test
    void getCurrencies_searchTermIsNull() {
        String searchTerm = null;
        when(currencyRepository.findAllWithExchangeRate()).thenReturn(Collections.emptyList());
        currencyService.getCurrencies(searchTerm);
        verify(currencyRepository).findAllWithExchangeRate();
    }

    @Test
    void getCurrencies_searchTerm() {
        String searchTerm = "Euro";
        when(currencyRepository.findAllBySearchTerm(searchTerm)).thenReturn(Collections.emptyList());
        currencyService.getCurrencies(searchTerm);
        verify(currencyRepository).findAllBySearchTerm(searchTerm);
    }

    @Test
    void getCurrency() {
        String code = "USD";
        when((currencyRepository.findByCode(code))).thenReturn(Optional.of(new Currency(1L, code, "US dollar", 2, "777")));
        currencyService.getCurrency(code);
        verify(currencyRepository).findByCode("USD");
    }

    @Test
    void getCurrency_throwsException() {
        String code = "USD";
        when((currencyRepository.findByCode(code))).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> currencyService.getCurrency(code));
    }
}