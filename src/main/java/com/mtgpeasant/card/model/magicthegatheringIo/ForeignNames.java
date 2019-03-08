package com.mtgpeasant.card.model.magicthegatheringIo;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

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
}
