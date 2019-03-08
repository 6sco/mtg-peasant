package com.mtgpeasant.card.model;

import com.mtgpeasant.card.model.magicthegatheringIo.Card;
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
