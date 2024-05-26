package ee.laus.exchange.client.response.exchange;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.time.LocalDate;
import java.util.List;

public record ExchangeRateApiResponse(
        @JacksonXmlProperty(localName = "Dt")
        LocalDate date,
        @JacksonXmlProperty(localName = "CcyAmt")
        @JacksonXmlElementWrapper(useWrapping = false)
        List<CurrencyAmountApiResponse> amounts
) {
}
