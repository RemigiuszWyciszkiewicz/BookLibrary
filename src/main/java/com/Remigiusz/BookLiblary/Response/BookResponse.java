package com.Remigiusz.BookLiblary.Response;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class BookResponse {

    public BookResponse() {
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
    private Double averageRating;
    private List<String> authors;
    private Set<String> categories;

}
