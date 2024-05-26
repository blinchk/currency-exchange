package ee.laus.exchange.controller;

import ee.laus.exchange.model.currency.request.ConvertCurrencyRequest;
import ee.laus.exchange.model.exchange.response.ExchangeRateSummary;
import ee.laus.exchange.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/exchange-rate")
@RequiredArgsConstructor
public class ExchangeRateController {
    private final ExchangeRateService exchangeRateService;

    @GetMapping("/{code}")
    public ExchangeRateSummary getExchangeRates(@PathVariable String code) {
        return exchangeRateService.getExchangeRates(code);
    }

    @PostMapping("/convert")
    public BigDecimal convert(@RequestBody ConvertCurrencyRequest request) {
        return exchangeRateService.convert(request);
    }
}
