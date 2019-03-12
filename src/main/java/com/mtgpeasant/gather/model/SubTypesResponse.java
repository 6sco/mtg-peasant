package com.mtgpeasant.gather.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class SubTypesResponse {

    List<String> subtypes;
}
