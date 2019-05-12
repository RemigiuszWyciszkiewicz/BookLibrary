package com.Remigiusz.BookLiblary.Response;

import lombok.Data;

import java.util.Set;

@Data
public class ResponeBook {

    private String isbn;
    private String title;
    private String subtitle;
    private long publishedDate;
    private String description;
    private int pageCount;
    private String thumbnailUrl;
    private Set<String> authors;
    private Set<String> categories;

}
