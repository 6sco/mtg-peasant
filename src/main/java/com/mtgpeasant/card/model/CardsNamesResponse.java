package com.mtgpeasant.card.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class CardsNamesResponse {

    private List<String> names;
}
