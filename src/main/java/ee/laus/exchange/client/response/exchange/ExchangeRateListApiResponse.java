package ee.laus.exchange.client.response.exchange;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "FxRates")
public record ExchangeRateListApiResponse(
        @JacksonXmlProperty(localName = "FxRate")
        @JacksonXmlElementWrapper(useWrapping = false)
        List<ExchangeRateApiResponse> exchangeRates
) {
}
