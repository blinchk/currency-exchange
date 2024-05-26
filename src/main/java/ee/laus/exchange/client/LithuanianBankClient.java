package ee.laus.exchange.client;

import ee.laus.exchange.client.response.currency.CurrencyListApiResponse;
import ee.laus.exchange.client.response.exchange.ExchangeRateListApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@Component
public class LithuanianBankClient {
    @Value("${application.lithuanian-bank-api.url}")
    private String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    public CurrencyListApiResponse getCurrencies() {
        String url = baseUrl + "/getCurrencyList";
        HttpEntity<Void> entity = new HttpEntity<>(getHeaders());
        return restTemplate.exchange(url, HttpMethod.GET, entity, CurrencyListApiResponse.class).getBody();
    }

    public ExchangeRateListApiResponse getExchangeRates(LocalDate startDate, LocalDate endDate) {
        String url = baseUrl + "/getFxRatesForCurrency";
        String currencyZone = "EU";
        URI uri = UriComponentsBuilder.fromUriString(url)
                          .queryParam("tp", currencyZone)
                          .queryParam("dtFrom", startDate)
                          .queryParam("dtTo", endDate)
                          .queryParam("ccy", "")
                          .build().toUri();
        HttpEntity<Void> entity = new HttpEntity<>(getHeaders());
        return restTemplate.exchange(uri, HttpMethod.GET, entity, ExchangeRateListApiResponse.class).getBody();
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(CONTENT_TYPE, APPLICATION_XML_VALUE);
        return headers;
    }
}
