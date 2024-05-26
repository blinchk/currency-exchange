package ee.laus.exchange.controller;

import ee.laus.exchange.response.CurrencyResponse;
import ee.laus.exchange.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyService currencyService;

    @GetMapping
    public List<CurrencyResponse> getCurrencies() {
        return currencyService.getCurrencies();
    }
}
