package com.mtgpeasant.configuration;

import com.mtgpeasant.card.CardGatherJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quartz.DateBuilder.futureDate;

@Configuration
public class QuartzConfiguration {

    @Value("${gather.repeat.interval:86400}")
    private Integer gatherRepeatIntervalInSecond;

    @Value("${trigger.at.boot.name}")
    private String triggerAtBootName;

    @Value("${trigger.every.day.name}")
    private String triggerEveryDayName;

    @Bean
    public JobDetail gatherCardJob() {
        return JobBuilder.newJob().ofType(CardGatherJob.class)
                .storeDurably()
                .withIdentity("gatherCardJob")
                .withDescription("Invoke gather card job.").build();
    }

    @Bean
    public Trigger gatherCardJobTriggerAtBoot(JobDetail gatherCardJob) {
        return TriggerBuilder.newTrigger().forJob(gatherCardJob)
                .withIdentity(triggerAtBootName).startNow().build();
    }

    @Bean
    public Trigger gatherCardJobTriggerEveryDay(JobDetail gatherCardJob) {

        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(gatherRepeatIntervalInSecond).repeatForever();

        return TriggerBuilder.newTrigger().forJob(gatherCardJob)
                .withIdentity(triggerEveryDayName).startAt(futureDate(gatherRepeatIntervalInSecond, DateBuilder
                        .IntervalUnit.SECOND)).withSchedule(scheduleBuilder).build();
    }
}