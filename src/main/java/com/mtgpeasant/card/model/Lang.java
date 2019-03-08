package com.mtgpeasant.card.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public enum Lang {

    en("English"),

    de("German"),

    es("Spanish"),

    fr("French"),

    it("Italian"),

    ja("Japanese"),

    pt("Portuguese (Brazil)"),

    ru("Russian"),

    zhs("Chinese Simplified"),

    zht("Chinese Traditional"),

    ko("Korean"),

    la("Latin"),

    grc("Ancient Greek"),

    sa("Sanskrit"),

    ar("Arabic"),

    he("Hebrew");

    private String value;

}
