package com.mtgpeasant.card.model;

import com.mtgpeasant.gather.model.Card;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class CardsResponse {

    private List<Card> cards;
}
