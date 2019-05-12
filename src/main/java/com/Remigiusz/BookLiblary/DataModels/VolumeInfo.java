package com.Remigiusz.BookLiblary.DataModels;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class VolumeInfo {

    public VolumeInfo() {
    }

    private String title;
    private Set<String> authors;
    private String publisher;
    private String publishedDate;
    private String description;
    private List<HashMap<String,String>> industryIdentifiers;
    private List<HashMap<String,Boolean>> readingModes;
    private int pageCount;
    private String printType;
    private Set<String> categories;
    private String maturityRating;
    private String allowAnonLogging;
    private String contentVersion;
    private List<HashMap<String,String>> imageLinks;
    private String language;
    private String previewLink;
    private String infoLink;
    private String canonicalVolumeLink;

}
