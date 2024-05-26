package ee.laus.exchange.client;

import ee.laus.exchange.client.currency.CurrencyListApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@Component
public class LithuanianBankClient {
    @Value("${application.lithuanian-bank-api.url}")
    private String BASE_URL;
    private final RestTemplate restTemplate = new RestTemplate();

    public CurrencyListApiResponse getCurrencies() {
        String url = BASE_URL + "/getCurrencyList";
        HttpHeaders headers = new HttpHeaders();
        headers.add(CONTENT_TYPE, APPLICATION_XML_VALUE);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity, CurrencyListApiResponse.class).getBody();
    }
}
