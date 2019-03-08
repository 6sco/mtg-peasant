package com.mtgpeasant.card;

import com.mtgpeasant.card.model.CardsNamesResponse;
import com.mtgpeasant.card.model.CardsResponse;
import com.mtgpeasant.card.model.Lang;
import com.mtgpeasant.card.model.RarityResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public CardsResponse getCards(@RequestParam(value = "page", required = false) Integer page,
                                  @RequestParam(value = "size", required = false) Integer size,
                                  @RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "set", required = false) String set) {

        LOGGER.debug("[getCards] called.");
        //TODO return page next link header
        return new CardsResponse(cardService.getCards(page, size, name, set));
    }

    @GetMapping(value = "/{cardName}/rarity", produces = "application/json")
    public RarityResponse getCardRarity(@PathVariable(value = "cardName") String cardName,
                                        @RequestParam(value = "lang", required = false) Lang lang) {

        LOGGER.debug("[getCardRarity] cardName {}, lang {}.", cardName, lang);

        return new RarityResponse(cardService.getCardRarity(cardName, lang));
    }

    @GetMapping(value = "/names", produces = "application/json")
    public CardsNamesResponse getCardsNames(@RequestParam(value = "partialName") String partialName,
                                            @RequestParam(value = "lang", required = false) Lang lang) {

        LOGGER.debug("[getCardsNames] partialName {}, lang {}.", partialName, lang);

        return new CardsNamesResponse(cardService.getCardsNames(partialName, lang));
    }
}
