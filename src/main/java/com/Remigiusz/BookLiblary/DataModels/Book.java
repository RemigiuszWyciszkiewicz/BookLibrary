package com.Remigiusz.BookLiblary.DataModels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {

    public Book() {
    }

    private String kind;
    private String id;
    private String etag;
    private String selfLink;

    private VolumeInfo volumeInfo;

}
