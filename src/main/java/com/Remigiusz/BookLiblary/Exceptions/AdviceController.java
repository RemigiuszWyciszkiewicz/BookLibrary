package com.Remigiusz.BookLiblary.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<SimpleMessage> simpleMessageResponseEntity (NotFoundException error)
    {
        return new ResponseEntity<SimpleMessage>(new SimpleMessage(HttpStatus.NOT_FOUND.value(),error.getMessage()),HttpStatus.NOT_FOUND);
    }

}
