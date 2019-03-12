package com.mtgpeasant.set.model;

import com.mtgpeasant.gather.model.Set;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class SetResponse {

    private List<Set> sets;
}
