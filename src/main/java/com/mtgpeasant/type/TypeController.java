package com.mtgpeasant.type;

import com.mtgpeasant.gather.model.SubTypesResponse;
import com.mtgpeasant.gather.model.SuperTypesResponse;
import com.mtgpeasant.gather.model.TypesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TypeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TypeController.class);

    private TypeService typeService;

    @Autowired
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping(value = "/types", produces = "application/json")
    public TypesResponse getTypes() {

        LOGGER.debug("[getTypes] called.");
        return new TypesResponse(typeService.getTypes());
    }

    @GetMapping(value = "/subtypes", produces = "application/json")
    public SubTypesResponse getSubtypes() {

        LOGGER.debug("[getSubtypes] called.");
        return new SubTypesResponse(typeService.getSubTypes());
    }

    @GetMapping(value = "/supertypes", produces = "application/json")
    public SuperTypesResponse getSupertypes() {

        LOGGER.debug("[getSupertypes] called.");
        return new SuperTypesResponse(typeService.getSuperTypes());
    }
}
