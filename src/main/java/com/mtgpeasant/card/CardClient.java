package com.mtgpeasant.card;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mtgpeasant.card.model.Card;
import com.mtgpeasant.card.model.Cards;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;

@Component
public class CardClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardClient.class);

    @Value("${magicTheGatheringIo.url:https://api.magicthegathering.io/v1}")
    private String magicTheGatheringIoURL;

    @Value("${proxy.host}")
    private String proxyHost;

    @Value("${proxy.port}")
    private Integer proxyPort;

    private OkHttpClient okHttpClient;

    public CardClient() {
        okHttpClient = new OkHttpClient();
        if (proxyHost != null && !proxyHost.isEmpty() && proxyPort != null) {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(proxyHost, proxyPort));
            okHttpClient.setProxy(proxy);
        }
    }

    /**
     * Get all cards from magicthegathering.io.
     *
     * @return a @{@link Card} list. May return an empty list if an error occurred while pulling cards from magicthegathering.io
     */
    List<Card> getCards() {

        LOGGER.debug("[getCards] called.");

        ArrayList<Card> cardsToReturn = new ArrayList<>();

        try {
            Integer next = 1;
            Boolean hasNext;
            Gson deserializer = new GsonBuilder().create();
            do {

                Request request = new Request.Builder().url(magicTheGatheringIoURL + "/cards?page=" + next).build();
                Response response = okHttpClient.newCall(request).execute();

                Cards cards = deserializer.fromJson(response.body().string(), Cards.class);

                LOGGER.debug("[getCards] get {} cards on page {}.", cards.getCards().size(), next);

                cardsToReturn.addAll(cards.getCards());

                if (response.header("link").contains("next")) {
                    hasNext = true;
                    next++;
                } else {
                    hasNext = false;
                }

            } while (hasNext);

        } catch (IOException e) {
            LOGGER.warn("[getCards] an error occurred while pulling cards from magicthegathering.io", e);
        }

        LOGGER.debug("[getCards] get {} cards from magicthegathering.io", cardsToReturn.size());

        return cardsToReturn;
    }
}