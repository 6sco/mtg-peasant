package com.mtgpeasant.gather.repository;

import com.mtgpeasant.gather.model.SuperType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperTypesRepository extends MongoRepository<SuperType, String> {

}