package com.mtgpeasant.card;

import com.mtgpeasant.card.model.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardService.class);

    private CardRepository cardRepository;

    private CardClient cardClient;

    @Autowired
    public CardService(CardRepository cardRepository, CardClient cardClient) {
        this.cardRepository = cardRepository;
        this.cardClient = cardClient;
    }

    /**
     * Gather all cards and save them to repository.
     */
    void gatherCards() {

        LOGGER.debug("[gatherCards] called.");

        List<Card> cards = cardClient.getCards();

        LOGGER.debug("[gatherCards] get {}.", cards.size());

        cardRepository.saveAll(cards);

        LOGGER.debug("[gatherCards] cards saves.");

    }

    /**
     * Get cards in repository.
     *
     * @param page the page number.
     * @param size the page size.
     * @return the card @{@link Page}
     */
    Page<Card> getAllCard(@NotNull Integer page, @NotNull Integer size) {

        LOGGER.debug("[getAllCard] page {}, size {}.", page, size);

        return cardRepository.findAll(PageRequest.of(page, size));
    }

    /**
     * Get a list of name cards by given a partial name. Ignore case.
     *
     * @param partialName the partial name to find cards name.
     * @return a @{@link List} of cards name.
     */
    List<String> getCardsName(String partialName) {

        List<Card> cards = cardRepository.getCardsByNameContainingIgnoreCase(partialName);

        return cards.stream().map(Card::getName).distinct().collect(Collectors.toList());
    }

    /**
     * Count the number of card in repository.
     *
     * @return count, the number of card in repository.
     */
    Long countCards() {

        long count = cardRepository.count();

        LOGGER.debug("[countCards] count {}.", count);

        return count;
    }
}