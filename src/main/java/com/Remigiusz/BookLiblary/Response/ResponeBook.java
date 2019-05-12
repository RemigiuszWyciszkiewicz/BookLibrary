package com.Remigiusz.BookLiblary.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class ResponeBook {

    public ResponeBook() {
    }

    private String isbn;

    private String title;
    private String subtitle;

    private String publisher;
    private long publishedDate;

    private String description;
    private int pageCount;

    private String thumbnailUrl;
    private String language;
    private String previewLink;
    private Double avarageRating;

    private Set<String> authors;
    private Set<String> categories;

}
