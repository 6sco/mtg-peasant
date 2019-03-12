package com.mtgpeasant.set;

import com.mtgpeasant.gather.model.Set;
import com.mtgpeasant.gather.repository.SetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SetService.class);

    private SetRepository setRepository;

    @Autowired
    public SetService(SetRepository setRepository) {
        this.setRepository = setRepository;
    }

    List<Set> getSets(String name, String code) {

        LOGGER.debug("[getSets] called with name {} and code {}.", name, code);

        if (name != null && !name.isEmpty() && code != null && !code.isEmpty()) {
            return setRepository.findByNameAndCode(name, code);
        }
        if (name != null && !name.isEmpty()) {
            return setRepository.findByName(name);
        }

        if (code != null && !code.isEmpty()) {
            return setRepository.findByCode(code);
        }
        return setRepository.findAll();
    }
}