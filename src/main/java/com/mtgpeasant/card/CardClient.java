package com.mtgpeasant.card;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mtgpeasant.card.model.Lang;
import com.mtgpeasant.card.model.magicthegatheringIo.Card;
import com.mtgpeasant.card.model.magicthegatheringIo.Cards;
import com.mtgpeasant.card.model.magicthegatheringIo.ForeignNames;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;

@Component
public class CardClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardClient.class);

    @Value("${magicTheGatheringIo.url:https://api.magicthegathering.io/v1}")
    private String magicTheGatheringIoURL;

    @Value("${proxy.host:}")
    private String proxyHost;

    @Value("${proxy.port:}")
    private Integer proxyPort;

    private OkHttpClient okHttpClient;

    private CardRepository cardRepository;

    private ForeignNamesRepository foreignNamesRepository;

    @Autowired
    public CardClient(CardRepository cardRepository, ForeignNamesRepository foreignNamesRepository) {
        this.cardRepository = cardRepository;
        this.foreignNamesRepository = foreignNamesRepository;
        okHttpClient = new OkHttpClient();
        if (proxyHost != null && !proxyHost.isEmpty() && proxyPort != null) {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(proxyHost, proxyPort));
            okHttpClient.setProxy(proxy);
        }
    }

    /**
     * Gather all cards from <a href="https://docs.magicthegathering.io/">https://magicthegathering.io/</a> and save them to repository.
     */
    void gatherCards() {

        LOGGER.debug("[getCards] called.");


        try {
            Integer next = 1;
            Boolean hasNext;
            Gson deserializer = new GsonBuilder().create();
            do {

                Request request = new Request.Builder().url(magicTheGatheringIoURL + "/cards?page=" + next).build();
                Response response = okHttpClient.newCall(request).execute();

                Cards cards = deserializer.fromJson(response.body().string(), Cards.class);

                LOGGER.debug("[getCards] get {} cards on page {}.", cards.getCards().size(), next);

                for (Card card : cards.getCards()) {

                    List<ForeignNames> foreignNames = card.getForeignNames();
                    foreignNames = (foreignNames == null) ? new ArrayList<>() : foreignNames;

                    foreignNames.add(new ForeignNames(card.getFlavor(), card.getMultiverseid(), card.getImageUrl(),
                            card.getName(), Lang.en.getValue(), card.getText()));

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

            LOGGER.debug("[getCards] get {} cards from https://api.magicthegathering.io", cardRepository.count());

        } catch (Exception e) {
            LOGGER.error("[getCards] an error occurred while pulling cards from https://api.magicthegathering.io", e);
        }
    }
}
