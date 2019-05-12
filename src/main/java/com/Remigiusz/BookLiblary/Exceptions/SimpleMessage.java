package com.Remigiusz.BookLiblary.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleMessage {

    public SimpleMessage() { }

    private int status;
    private String msg;
}
