package com.mtgpeasant.card;

import com.mtgpeasant.card.model.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardController.class);

    private CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping(produces = "application/json")
    public List<Card> getCards(@RequestParam(value = "page", required = false) Integer page,
                               @RequestParam(value = "size", required = false) Integer size) {

        LOGGER.debug("[getCards] called.");

        // Check params
        page = (page == null) ? 0 : page;
        size = (size == null) ? 100 : size;

        return cardService.getAllCard(page, size).getContent();
    }

    @GetMapping(value = "/names", produces = "application/json")
    public List<String> getCardsNameByPartialName(@RequestParam(value = "partialName") String partialName) {

        LOGGER.debug("[getCardsByPartialName] partialName {}.", partialName);

        return cardService.getCardsName(partialName);
    }
}