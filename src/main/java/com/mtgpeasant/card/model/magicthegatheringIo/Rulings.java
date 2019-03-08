package com.mtgpeasant.card.model.magicthegatheringIo;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class Rulings implements Serializable {

    private Date date;

    private String text;
}
