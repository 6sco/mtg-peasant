package com.mtgpeasant.card;

import com.mtgpeasant.card.model.magicthegatheringIo.Card;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends MongoRepository<Card, String> {

    List<Card> findCardsByForeignNames_Name(String name);

    List<Card> findCardsByForeignNames_NameAndForeignNames_Language(String name, String language);

}