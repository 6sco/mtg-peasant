package com.mtgpeasant.set;

import com.mtgpeasant.set.model.SetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sets")
public class SetController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SetController.class);

    private SetService setService;

    @Autowired
    public SetController(SetService setService) {
        this.setService = setService;
    }

    @GetMapping(produces = "application/json")
    public SetResponse getSets(@RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "code", required = false) String code) {

        LOGGER.debug("[getSets] called with name {} and code {}.", name, code);
        return new SetResponse(setService.getSets(name, code));
    }
}
