package com.mtgpeasant.gather.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Document(collection = "card")
public class Card implements Serializable {

    @Id
    private String id;

    private String name;

    private List<String> names;

    private String manaCost;

    private Float cmc;

    private List<String> colors;

    private List<String> colorIdentity;

    private String type;

    private List<String> supertypes;

    private List<String> types;

    private List<String> subtypes;

    private String rarity;

    private String set;

    private String setName;

    private String text;

    private String artist;

    private String number;

    private String power;

    private String toughness;

    private String loyalty;

    private Integer multiverseid;

    private String imageUrl;

    private String layout;

    private List<Legalities> legalities;

    private List<Rulings> rulings;

    private List<ForeignNames> foreignNames;

    private String originalText;

    private String flavor;

    private List<String> variations;

    private String watermark;

    private String border;

    private Boolean timeshifted;

    private String hand;

    private String life;

    private Boolean reserved;

    private String releaseDate;

    private Boolean starter;

    private List<String> printings;

    private String originalType;

    private String source;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();
}