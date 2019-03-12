package com.mtgpeasant.gather.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
class Rulings implements Serializable {

    private Date date;

    private String text;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();
}
