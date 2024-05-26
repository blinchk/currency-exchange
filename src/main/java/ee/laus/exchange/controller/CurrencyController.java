package ee.laus.exchange.controller;

import ee.laus.exchange.model.currency.CurrencyResponse;
import ee.laus.exchange.response.CurrencyListItem;
import ee.laus.exchange.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyService currencyService;

    @GetMapping
    public List<CurrencyListItem> getCurrencies(@RequestParam String searchTerm) {
        return currencyService.getCurrencies(searchTerm);
    }

    @GetMapping("/{code}")
    public CurrencyResponse getCurrency(@PathVariable String code) {
        return currencyService.getCurrency(code);
    }
}
