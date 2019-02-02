package com.mtgpeasant.card;

import com.mtgpeasant.card.model.Card;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends MongoRepository<Card, String> {

    List<Card> getCardsByNameContainingIgnoreCase(String partialName);

}