package com.Remigiusz.BookLiblary.Response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorRatingResponse {

    private String author;
    private Double rating;

}
