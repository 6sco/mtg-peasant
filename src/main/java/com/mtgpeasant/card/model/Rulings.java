package com.mtgpeasant.card.model;

import java.io.Serializable;
import java.util.Date;

public class Rulings implements Serializable {

    /**
     * The rule release date.
     */
    private Date date;

    /**
     * The rule text.
     */
    private String text;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
