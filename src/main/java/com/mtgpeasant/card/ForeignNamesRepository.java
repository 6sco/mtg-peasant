package com.mtgpeasant.card;

import com.mtgpeasant.card.model.magicthegatheringIo.ForeignNames;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForeignNamesRepository extends MongoRepository<ForeignNames, String> {

    List<ForeignNames> getForeignNamesByNameContainingIgnoreCase(String name);

    List<ForeignNames> getForeignNamesByNameStartingWithIgnoreCase(String name);

    List<ForeignNames> getForeignNamesByNameContainingIgnoreCaseAndLanguage(String name, String language);

    List<ForeignNames> getForeignNamesByNameStartingWithIgnoreCaseAndLanguage(String name, String language);
}