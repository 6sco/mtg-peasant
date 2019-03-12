package com.mtgpeasant.gather;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class DataGatherJob extends QuartzJobBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataGatherJob.class);

    @Value("${trigger.at.boot.name}")
    private String triggerAtBootName;

    private MagicGatheringIOClient magicGatheringIOClient;

    @Autowired
    public DataGatherJob(MagicGatheringIOClient magicGatheringIOClient) {
        this.magicGatheringIOClient = magicGatheringIOClient;
    }

    @Override
    protected void executeInternal(@NonNull JobExecutionContext context) {

        LOGGER.debug("[executeInternal] called.");

        // Check if it's a boot trigger.
        if (context.getTrigger().getKey().getName().equals(triggerAtBootName)) {
            magicGatheringIOClient.gatherData(true);
        } else {
            // Every week trigger, gather data.
            magicGatheringIOClient.gatherData(false);
        }
    }
}