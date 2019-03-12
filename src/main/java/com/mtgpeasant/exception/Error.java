package com.mtgpeasant.exception;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Error {

    private Code code;
    private String description;

}
