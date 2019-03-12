package com.mtgpeasant.gather.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Document(collection = "set")
public class Set {

    private String code;

    private String releaseDate;

    private String name;

    private String type;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();
}
