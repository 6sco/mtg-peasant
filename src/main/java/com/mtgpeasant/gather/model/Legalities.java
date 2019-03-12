package com.mtgpeasant.gather.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
class Legalities implements Serializable {

    private String legality;

    private String format;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();
}
