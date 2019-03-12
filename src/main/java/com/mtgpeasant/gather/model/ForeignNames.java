package com.mtgpeasant.gather.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@Document(collection = "foreignNames")
public class ForeignNames implements Serializable {

    private String flavor;

    private Integer multiverseid;

    private String imageUrl;

    private String name;

    private String language;

    private String text;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();
}
