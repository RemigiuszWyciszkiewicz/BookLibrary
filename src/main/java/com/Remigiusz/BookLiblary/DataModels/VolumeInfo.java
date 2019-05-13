package com.Remigiusz.BookLiblary.DataModels;

import com.Remigiusz.BookLiblary.Deserializers.DifferentFormatsDateJsonDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class VolumeInfo {

    public VolumeInfo() {
    }

    private String title;
    private List<String> authors;
    private String publisher;
    @JsonDeserialize(using = DifferentFormatsDateJsonDeserializer.class)
    private Long publishedDate = Long.valueOf(9999999);
    private String description;
    private String subtitle;
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

