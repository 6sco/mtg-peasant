package com.mtgpeasant.card.model;

import java.io.Serializable;

public class ForeignNames implements Serializable {

    /**
     * The foreign flavor text of the card.
     */
    private String flavor;

    /**
     * The foreign multiverseid of the card on Wizard’s Gatherer web page.
     */
    private Integer multiverseid;

    /**
     * The image url for the foreign card. Only exists if the card has a multiverse id.
     */
    private String imageUrl;

    /**
     * The foreign card name. For split, double-faced and flip cards, just the name of one side of the card.
     */
    private String name;

    /**
     * The foreign language.
     */
    private String language;

    /**
     * The oracle foreign text of the card. May contain mana symbols and other symbols.
     */
    private String text;

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public Integer getMultiverseid() {
        return multiverseid;
    }

    public void setMultiverseid(Integer multiverseid) {
        this.multiverseid = multiverseid;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}