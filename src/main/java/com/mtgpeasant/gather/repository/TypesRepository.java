package com.mtgpeasant.gather.repository;

import com.mtgpeasant.gather.model.Type;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypesRepository extends MongoRepository<Type, String> {

}