package ee.laus.exchange.client.currency;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public record CurrencyApiResponse(
        @JacksonXmlProperty(localName = "Ccy")
        String code,
        @JacksonXmlProperty(localName = "CcyNm")
        @JacksonXmlElementWrapper(useWrapping = false)
        List<CurrencyNameApiResponse> names,
        @JacksonXmlProperty(localName = "CcyNbr")
        String numericCode,
        @JacksonXmlProperty(localName = "CcyMnrUnts")
        Integer minorUnits
) {
        private static final String ENGLISH_LANGUAGE_CODE = "EN";

        public String name() {
                return names.stream()
                        .filter(name -> name.getLanguage().equals(ENGLISH_LANGUAGE_CODE))
                        .findFirst()
                        .map(CurrencyNameApiResponse::getValue)
                        .orElse(null);
        }
}
