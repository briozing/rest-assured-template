package com.briozing.automation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegionalBlocDTO {

    private String acronym;

    private String name;

    private List<String> otherAcronyms;

    private List<String> otherNames;

}
