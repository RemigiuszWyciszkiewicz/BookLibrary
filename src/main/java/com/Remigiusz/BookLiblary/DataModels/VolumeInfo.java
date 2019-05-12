package com.Remigiusz.BookLiblary.DataModels;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class VolumeInfo {

    public VolumeInfo() {
    }

    private String title;
    private Set<String> authors;
    private String publisher;
    private Date publishedDate;
    private String description;
    private String subtitle;
    /*private List<HashMap<String,String>> industryIdentifiers;*/
    private List<IndustryIdentifiers> industryIdentifiers;
    private List<HashMap<String,Boolean>> readingModes;
    private int pageCount;
    private String printType;
    private Set<String> categories;
    private String maturityRating;
    private String allowAnonLogging;
    private String contentVersion;
    private HashMap<String,String> imageLinks;
    private String language;
    private String previewLink;
    private String infoLink;
    private String canonicalVolumeLink;
    private Double averageRating;




}

