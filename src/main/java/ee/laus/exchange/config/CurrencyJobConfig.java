package ee.laus.exchange.config;

import ee.laus.exchange.job.CurrencySaveJob;
import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CurrencyJobConfig {

    @Bean
    public JobDetail currencySaveJobDetail() {
        return JobBuilder.newJob(CurrencySaveJob.class)
                .withIdentity("currencySaveJob", "currencyJobs")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger currencySaveJobTrigger() {
        return TriggerBuilder.newTrigger()
                .withIdentity("currencySaveJobTrigger")
                .forJob(currencySaveJobDetail())
                .startNow()
                .withSchedule(SimpleScheduleBuilder
                        .repeatHourlyForever()
                        .withIntervalInHours(24))
                .build();
    }
}
