package com.mtgpeasant.card.model;

import java.io.Serializable;

public enum Layout implements Serializable {

    NORMAL("normal"),
    SPLIT("split"),
    FLIP("flip"),
    DOUBLE_FACED("double-faced"),
    TOKEN("token"),
    PLANE("plane"),
    SCHEME("scheme"),
    PHENOMENON("phenomenon"),
    LEVELER("leveler"),
    VANGUARD("vanguard"),
    AFTERMATH("aftermath");

    private final String value;

    Layout(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}