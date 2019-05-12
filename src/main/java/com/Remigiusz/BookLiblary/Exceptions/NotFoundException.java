package com.Remigiusz.BookLiblary.Exceptions;

import org.aspectj.lang.annotation.DeclareError;


public class NotFoundException extends RuntimeException{

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }
}
