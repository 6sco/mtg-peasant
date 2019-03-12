package com.mtgpeasant.gather.repository;

import com.mtgpeasant.gather.model.Set;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SetRepository extends MongoRepository<Set, String> {

    List<Set> findByCode(String code);

    List<Set> findByName(String name);

    List<Set> findByNameAndCode(String name, String code);
}