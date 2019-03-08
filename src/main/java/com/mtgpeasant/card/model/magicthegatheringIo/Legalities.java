package com.mtgpeasant.card.model.magicthegatheringIo;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class Legalities implements Serializable {

    private String legality;

    private String format;
}
