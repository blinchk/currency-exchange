package ee.laus.exchange.config.job;


import ee.laus.exchange.job.ExchangeRateSaveJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangeRateJobConfig {
    @Bean
    public JobDetail exchangeRateSaveJobDetail() {
        return JobBuilder.newJob(ExchangeRateSaveJob.class)
                .withIdentity("exchangeRateSaveJob", "exchangeRateJobs")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger exchangeRateSaveJobTrigger() {
        return TriggerBuilder.newTrigger()
                .withIdentity("exchangeRateSaveJobTrigger")
                .forJob(exchangeRateSaveJobDetail())
                .startNow()
                .withSchedule(SimpleScheduleBuilder
                        .repeatHourlyForever()
                        .withIntervalInHours(24))
                .build();
    }

}
