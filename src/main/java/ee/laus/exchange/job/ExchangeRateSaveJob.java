package ee.laus.exchange.job;

import ee.laus.exchange.client.LithuanianBankClient;
import ee.laus.exchange.client.response.exchange.ExchangeRateListApiResponse;
import ee.laus.exchange.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ExchangeRateSaveJob extends QuartzJobBean {
    private final LithuanianBankClient client;
    private final ExchangeRateService service;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(30);
        ExchangeRateListApiResponse response = client.getExchangeRates(startDate, endDate);
        service.upload(response);
    }
}
