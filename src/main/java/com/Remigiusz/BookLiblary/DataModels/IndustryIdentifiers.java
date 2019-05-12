package com.Remigiusz.BookLiblary.DataModels;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IndustryIdentifiers {

    public IndustryIdentifiers() {
    }

    private String type;
    private String identifier;
}