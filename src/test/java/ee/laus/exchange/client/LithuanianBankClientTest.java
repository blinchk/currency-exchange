package ee.laus.exchange.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LithuanianBankClientTest {
    @Autowired private LithuanianBankClient client;

    @Test
    void getCurrencies() {
        System.out.println(client.getCurrencies());
    }
}