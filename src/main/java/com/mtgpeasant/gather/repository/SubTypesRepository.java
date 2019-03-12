package com.mtgpeasant.gather.repository;

import com.mtgpeasant.gather.model.SubType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubTypesRepository extends MongoRepository<SubType, String> {

}