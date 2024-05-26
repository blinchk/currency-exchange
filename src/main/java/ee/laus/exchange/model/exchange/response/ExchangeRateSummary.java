package ee.laus.exchange.model.exchange.response;

import java.util.List;

public record ExchangeRateSummary(
        List<ExchangeRateListItem> rates,
        ExchangeRateListItem current,
        ExchangeRateListItem max,
        ExchangeRateListItem min
) {
}
