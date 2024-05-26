package ee.laus.exchange.client.response.exchange;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public record CurrencyAmountApiResponse(
        @JacksonXmlProperty(localName = "Ccy")
        String code,
        @JacksonXmlProperty(localName = "Amt")
        Double amount
) {
}
