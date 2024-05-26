package ee.laus.exchange.model.exchange.dto;

import ee.laus.exchange.client.response.exchange.CurrencyAmountApiResponse;
import ee.laus.exchange.client.response.exchange.ExchangeRateApiResponse;
import ee.laus.exchange.exception.IllegalExchangeRateException;

import java.time.LocalDate;

import static ee.laus.exchange.util.CurrencyUtil.DEFAULT_CURRENCY;

public record ExchangeRateDto(
        String code,
        Double amount,
        LocalDate date
) {

    public static ExchangeRateDto of(ExchangeRateApiResponse response) {
        CurrencyAmountApiResponse amount = response.amounts().stream()
                .filter(responseAmount -> !responseAmount.code().equals(DEFAULT_CURRENCY))
                .findFirst()
                .orElseThrow(IllegalExchangeRateException::new);
        return new ExchangeRateDto(
                amount.code(),
                amount.amount(),
                response.date()
        );
    }
}
