package com.mtgpeasant.card;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class CardGatherJob extends QuartzJobBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardGatherJob.class);

    @Value("${trigger.at.boot.name}")
    private String triggerAtBootName;

    private CardClient cardClient;

    private CardRepository cardRepository;

    @Autowired
    public CardGatherJob(CardClient cardClient, CardRepository cardRepository) {
        this.cardRepository = cardRepository;
        this.cardClient = cardClient;
    }

    @Override
    protected void executeInternal(@NonNull JobExecutionContext context) {

        LOGGER.debug("[executeInternal] called.");

        // Check if it's a boot trigger.
        if (context.getTrigger().getKey().getName().equals(triggerAtBootName)) {

            // Boot trigger, gather cards only if no cards are found in repository.
            Long countCards = cardRepository.count();
            LOGGER.debug("[executeInternal] countCards {}", countCards);

            if (countCards.equals(0L)) {
                cardClient.gatherCards();
            }

        } else {
            // Every week trigger, gather cards.
            cardClient.gatherCards();
        }
    }
}