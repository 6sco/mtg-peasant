package com.mtgpeasant.card.model.magicthegatheringIo;

import lombok.*;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class Cards {

    private Collection<Card> cards;
}
