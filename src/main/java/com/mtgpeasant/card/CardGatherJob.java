package com.mtgpeasant.card;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class CardGatherJob extends QuartzJobBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardGatherJob.class);

    @Value("${trigger.at.boot.name}")
    private String triggerAtBootName;

    private CardService gatherService;

    @Autowired
    public CardGatherJob(CardService gatherService) {
        this.gatherService = gatherService;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        LOGGER.debug("[executeInternal] called.");

        // Check if it's a boot trigger.
        if (context.getTrigger().getKey().getName().equals(triggerAtBootName)) {

            // Boot trigger, gather cards only if no cards are found in repository.
            Long countCards = gatherService.countCards();
            LOGGER.debug("[executeInternal] countCards {}", countCards);

            if (countCards.equals(0L)) {
                gatherService.gatherCards();
            }

        } else {
            // Every day trigger, gather cards.
            gatherService.gatherCards();
        }
    }
}