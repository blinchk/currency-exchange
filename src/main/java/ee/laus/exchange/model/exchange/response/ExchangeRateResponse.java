package ee.laus.exchange.model.exchange.response;

import java.time.LocalDate;

public record ExchangeRateResponse(
        Double amount,
        LocalDate date
) {
}
