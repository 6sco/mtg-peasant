package com.mtgpeasant.gather.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class Sets {

    private List<Set> sets;
}
