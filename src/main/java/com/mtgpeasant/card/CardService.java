package com.mtgpeasant.card;

import com.mtgpeasant.card.Exception.CardNotFoundException;
import com.mtgpeasant.card.model.Lang;
import com.mtgpeasant.card.model.Rarity;
import com.mtgpeasant.card.model.magicthegatheringIo.Card;
import com.mtgpeasant.card.model.magicthegatheringIo.ForeignNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardService.class);

    private CardRepository cardRepository;

    private ForeignNamesRepository foreignNamesRepository;

    private MongoTemplate mongoTemplate;

    @Autowired
    public CardService(CardRepository cardRepository, ForeignNamesRepository foreignNamesRepository, MongoTemplate mongoTemplate) {
        this.cardRepository = cardRepository;
        this.foreignNamesRepository = foreignNamesRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public List<Card> getCards(Integer page, Integer size, String name, String set) {

        LOGGER.debug("[getCards] page {}, size {}, name {}, set {}.", page, size, name, set);

        Query query = new Query();

        // Default page 0, default size 100.
        page = (page == null || page < 0) ? 0 : page;
        size = (size == null || size < 1) ? 100 : size;
        query.with(PageRequest.of(page, size));

        if (set != null && !set.isEmpty()) {
            query.addCriteria(Criteria.where("set").is(set));
        }

        if (name != null && !name.isEmpty()) {
            query.addCriteria(Criteria.where("foreignNames_name").is(name));
        }

        return mongoTemplate.find(query, Card.class);
    }

    public List<String> getCardsNames(String partialName, Lang lang) {

        LOGGER.debug("[getCardsNames] partialName {}, lang {}", partialName, lang);

        List<ForeignNames> foreignNames = new ArrayList<>();

        if (lang != null) {
            foreignNames = foreignNamesRepository.getForeignNamesByNameStartingWithIgnoreCaseAndLanguage(partialName, lang.getValue());
            if (foreignNames.isEmpty()) {
                foreignNames = foreignNamesRepository.getForeignNamesByNameContainingIgnoreCaseAndLanguage(partialName, lang.getValue());
            }
        }

        if (foreignNames.isEmpty()) {
            foreignNames = foreignNamesRepository.getForeignNamesByNameStartingWithIgnoreCase(partialName);
            if (foreignNames.isEmpty()) {
                foreignNames = foreignNamesRepository.getForeignNamesByNameContainingIgnoreCase(partialName);
            }
        }

        return foreignNames.stream().map(ForeignNames::getName).distinct().collect(Collectors.toList());
    }

    public Rarity getCardRarity(String cardName, Lang lang) {

        LOGGER.debug("[getCardRarity] cardName {}, lang {}", cardName, lang);

        List<Card> cards = new ArrayList<>();

        if (lang == null) {
            cards.addAll(cardRepository.findCardsByForeignNames_Name(cardName));
        } else {
            cards.addAll(cardRepository.findCardsByForeignNames_NameAndForeignNames_Language(cardName, lang.getValue()));
        }

        if (cards.isEmpty()) throw new CardNotFoundException(cardName + ", not found for lang " + lang);

        Rarity currentRarity = Rarity.MYTHIC;

        for (Card card : cards) {
            Rarity cardRarity = Rarity.fromStringRarity(card.getRarity());
            if (cardRarity != null && cardRarity.isLowestThan(currentRarity)) {
                currentRarity = cardRarity;
            }
        }
        return currentRarity;
    }
}