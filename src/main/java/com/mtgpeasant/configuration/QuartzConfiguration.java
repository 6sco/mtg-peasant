package com.mtgpeasant.configuration;

import com.mtgpeasant.gather.DataGatherJob;
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
    private String triggerEveryWeekName;

    @Bean
    public JobDetail gatherDataJob() {
        return JobBuilder.newJob().ofType(DataGatherJob.class)
                .storeDurably()
                .withIdentity("gatherDataJob")
                .withDescription("Invoke gather data job.").build();
    }

    @Bean
    public Trigger gatherDataJobTriggerAtBoot(JobDetail gatherDataJob) {
        return TriggerBuilder.newTrigger().forJob(gatherDataJob)
                .withIdentity(triggerAtBootName).startNow().build();
    }

    @Bean
    public Trigger gatherDataJobTriggerEveryWeek(JobDetail gatherDataJob) {

        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInHours(gatherRepeatIntervalInWeek * 7 * 24).repeatForever();

        return TriggerBuilder.newTrigger().forJob(gatherDataJob)
                .withIdentity(triggerEveryWeekName).startAt(futureDate(gatherRepeatIntervalInWeek, DateBuilder
                        .IntervalUnit.WEEK)).withSchedule(scheduleBuilder).build();
    }
}