package com.mtgpeasant.gather;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mtgpeasant.card.model.Lang;
import com.mtgpeasant.gather.model.*;
import com.mtgpeasant.gather.repository.*;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MagicGatheringIOClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(MagicGatheringIOClient.class);

    @Value("${magicTheGatheringIo.url:https://api.magicthegathering.io/v1}")
    private String magicTheGatheringIoURL;

    private OkHttpClient okHttpClient;

    private CardRepository cardRepository;

    private ForeignNamesRepository foreignNamesRepository;

    private SetRepository setRepository;

    private SubTypesRepository subTypesRepository;

    private SuperTypesRepository superTypesRepository;

    private TypesRepository typesRepository;

    private Gson deserializer;

    @Autowired
    public MagicGatheringIOClient(OkHttpClient okHttpClient, CardRepository cardRepository,
                                  ForeignNamesRepository foreignNamesRepository, SetRepository setRepository,
                                  SubTypesRepository subTypesRepository, SuperTypesRepository superTypesRepository,
                                  TypesRepository typesRepository) {
        this.okHttpClient = okHttpClient;
        this.cardRepository = cardRepository;
        this.foreignNamesRepository = foreignNamesRepository;
        this.setRepository = setRepository;
        this.typesRepository = typesRepository;
        this.subTypesRepository = subTypesRepository;
        this.superTypesRepository = superTypesRepository;

        deserializer = new GsonBuilder().create();
    }

    void gatherData(Boolean isBoot) {

        LOGGER.debug("[gatherData] called.");

        if (!isBoot || setRepository.count() == 0) {
            gatherSet();
            LOGGER.debug("[gatherData] gatherSet() done.");
        }

        if (!isBoot || typesRepository.count() == 0) {
            gatherType();
            LOGGER.debug("[gatherData] gatherType() done.");
        }

        if (!isBoot || subTypesRepository.count() == 0) {
            gatherSubTypes();
            LOGGER.debug("[gatherData] gatherSubTypes() done.");
        }

        if (!isBoot || superTypesRepository.count() == 0) {
            gatherSuperType();
            LOGGER.debug("[gatherData] gatherSuperType() done.");
        }

        if (!isBoot || cardRepository.count() == 0) {
            gatherCards();
            LOGGER.debug("[gatherData] gatherCards() done.");
        }
    }

    private void gatherSet() {

        try {
            Request request = new Request.Builder().url(magicTheGatheringIoURL + "/sets").build();
            Response response = okHttpClient.newCall(request).execute();

            Sets sets = deserializer.fromJson(response.body().string(), Sets.class);
            LOGGER.debug("[gatherSet] get {} sets.", sets.getSets().size());

            setRepository.saveAll(sets.getSets());

        } catch (Exception e) {
            LOGGER.error("[gatherSet] an error occurred while pulling sets from https://api.magicthegathering.io/v1/sets", e);
        }
    }

    private void gatherType() {

        try {
            Request request = new Request.Builder().url(magicTheGatheringIoURL + "/types").build();
            Response response = okHttpClient.newCall(request).execute();

            TypesResponse typesResponse = deserializer.fromJson(response.body().string(), TypesResponse.class);
            LOGGER.debug("[gatherType] get {} types.", typesResponse.getTypes().size());

            for (String type : typesResponse.getTypes()) {
                typesRepository.save(new Type(type));
            }

        } catch (Exception e) {
            LOGGER.error("[gatherType] an error occurred while pulling types from https://api.magicthegathering.io/v1/types", e);
        }
    }

    private void gatherSubTypes() {

        try {
            Request request = new Request.Builder().url(magicTheGatheringIoURL + "/subtypes").build();
            Response response = okHttpClient.newCall(request).execute();

            SubTypesResponse subTypesResponse = deserializer.fromJson(response.body().string(), SubTypesResponse.class);
            LOGGER.debug("[gatherSubTypes] get {} subTypes.", subTypesResponse.getSubtypes().size());

            for (String subtype : subTypesResponse.getSubtypes()) {
                subTypesRepository.save(new SubType(subtype));
            }

        } catch (Exception e) {
            LOGGER.error("[gatherSubTypes] an error occurred while pulling subtypes from https://api.magicthegathering.io/v1/subtypes", e);
        }
    }

    private void gatherSuperType() {

        try {
            Request request = new Request.Builder().url(magicTheGatheringIoURL + "/supertypes").build();
            Response response = okHttpClient.newCall(request).execute();

            SuperTypesResponse superTypesResponse = deserializer.fromJson(response.body().string(), SuperTypesResponse.class);
            LOGGER.debug("[gatherSuperType] get {} superTypes.", superTypesResponse.getSupertypes().size());

            for (String supertype : superTypesResponse.getSupertypes()) {
                superTypesRepository.save(new SuperType(supertype));
            }

        } catch (Exception e) {
            LOGGER.error("[gatherSuperType] an error occurred while pulling supertypes from https://api.magicthegathering.io/v1/supertypes", e);
        }
    }

    private void gatherCards() {
        try {
            int next = 1;
            boolean hasNext;
            do {

                Request request = new Request.Builder().url(magicTheGatheringIoURL + "/cards?page=" + next).build();
                Response response = okHttpClient.newCall(request).execute();

                Cards cards = deserializer.fromJson(response.body().string(), Cards.class);

                LOGGER.debug("[gatherData] get {} cards on page {}.", cards.getCards().size(), next);

                for (Card card : cards.getCards()) {

                    List<ForeignNames> foreignNames = card.getForeignNames();
                    foreignNames = (foreignNames == null) ? new ArrayList<>() : foreignNames;

                    foreignNames.add(new ForeignNames(card.getFlavor(), card.getMultiverseid(), card.getImageUrl(),
                            card.getName(), Lang.en.getValue(), card.getText(), null));

                    card.setForeignNames(foreignNames);
                    foreignNamesRepository.saveAll(foreignNames);

                }

                cardRepository.saveAll(cards.getCards());

                if (response.header("link").contains("next")) {
                    hasNext = true;
                    next++;
                } else {
                    hasNext = false;
                }

            } while (hasNext);

            LOGGER.debug("[gatherData] get {} cards from https://api.magicthegathering.io", cardRepository.count());

        } catch (Exception e) {
            LOGGER.error("[gatherData] an error occurred while pulling card from https://api.magicthegathering.io/v1/cards", e);
        }
    }
}
