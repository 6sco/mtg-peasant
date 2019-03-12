package com.mtgpeasant.gather.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Document(collection = "superType")
public class SuperType {

    private String supertype;
}
