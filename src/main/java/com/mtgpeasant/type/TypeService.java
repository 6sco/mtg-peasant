package com.mtgpeasant.type;

import com.mtgpeasant.gather.model.SubType;
import com.mtgpeasant.gather.model.SuperType;
import com.mtgpeasant.gather.model.Type;
import com.mtgpeasant.gather.repository.SubTypesRepository;
import com.mtgpeasant.gather.repository.SuperTypesRepository;
import com.mtgpeasant.gather.repository.TypesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TypeService.class);

    private TypesRepository typesRepository;

    private SubTypesRepository subTypesRepository;

    private SuperTypesRepository superTypesRepository;

    @Autowired
    public TypeService(TypesRepository typesRepository, SubTypesRepository subTypesRepository, SuperTypesRepository superTypesRepository) {
        this.typesRepository = typesRepository;
        this.subTypesRepository = subTypesRepository;
        this.superTypesRepository = superTypesRepository;
    }

    List<String> getTypes() {
        LOGGER.debug("[getTypes] called.");
        return typesRepository.findAll().stream().map(Type::getType).collect(Collectors.toList());
    }

    List<String> getSubTypes() {
        LOGGER.debug("[getSubTypes] called.");
        return subTypesRepository.findAll().stream().map(SubType::getSubtype).collect(Collectors.toList());
    }

    List<String> getSuperTypes() {
        LOGGER.debug("[getSuperTypes] called.");
        return superTypesRepository.findAll().stream().map(SuperType::getSupertype).collect(Collectors.toList());
    }
}