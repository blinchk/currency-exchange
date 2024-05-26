package ee.laus.exchange.job;

import ee.laus.exchange.client.LithuanianBankClient;
import ee.laus.exchange.service.CurrencyService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrencySaveJob extends QuartzJobBean {
    private final LithuanianBankClient client;
    private final CurrencyService service;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        var currencies = client.getCurrencies();
        service.upload(currencies);
    }
}
