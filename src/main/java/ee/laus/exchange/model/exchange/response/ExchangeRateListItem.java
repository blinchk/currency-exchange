package ee.laus.exchange.model.exchange.response;

import java.time.LocalDate;

public record ExchangeRateListItem(
        Double amount,
        LocalDate date
) {
}
