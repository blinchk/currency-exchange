package ee.laus.exchange.client.currency;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "CcyTbl")
public record CurrencyListApiResponse(
        @JacksonXmlProperty(localName = "CcyNtry")
        @JacksonXmlElementWrapper(useWrapping = false)
        List<CurrencyApiResponse> currencies
) {
}
