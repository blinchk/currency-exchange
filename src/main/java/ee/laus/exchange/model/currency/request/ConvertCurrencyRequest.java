package ee.laus.exchange.model.currency.request;

import java.math.BigDecimal;

public record ConvertCurrencyRequest(
        BigDecimal amount,
        String code
) {
}
