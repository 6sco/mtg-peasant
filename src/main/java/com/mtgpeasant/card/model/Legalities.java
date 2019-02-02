package com.mtgpeasant.card.model;

import java.io.Serializable;

public class Legalities implements Serializable {

    /**
     * Card is legal, restricted or banned for the given format.
     */
    private String legality;

    /**
     * The format to check the legality.
     */
    private String format;

    public String getLegality() {
        return legality;
    }

    public void setLegality(String legality) {
        this.legality = legality;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
