package ee.laus.exchange.client.response.currency;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CurrencyNameApiResponse {
    @JacksonXmlText
    private String value;
    @JacksonXmlProperty(isAttribute = true, localName = "lang")
    private String language;
}
