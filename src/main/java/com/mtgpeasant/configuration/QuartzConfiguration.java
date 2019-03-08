package com.mtgpeasant.configuration;

import com.mtgpeasant.card.CardGatherJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quartz.DateBuilder.futureDate;

@Configuration
public class QuartzConfiguration {

    @Value("${gather.repeat.interval:1}")
    private Integer gatherRepeatIntervalInWeek;

    @Value("${trigger.at.boot.name}")
    private String triggerAtBootName;

    @Value("${trigger.every.week.name}")
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
                .withIntervalInHours(gatherRepeatIntervalInWeek * 7 * 24).repeatForever();

        return TriggerBuilder.newTrigger().forJob(gatherCardJob)
                .withIdentity(triggerEveryDayName).startAt(futureDate(gatherRepeatIntervalInWeek, DateBuilder
                        .IntervalUnit.WEEK)).withSchedule(scheduleBuilder).build();
    }
}