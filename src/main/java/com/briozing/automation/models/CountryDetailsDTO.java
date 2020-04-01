package com.briozing.automation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class CountryDetailsDTO {

    private String name;

    private String alpha2Code;

    private String alpha3Code;

    private List<String> callingCodes;

    private String capital;

    private List<String> altSpellings;

    private String region;

    @JsonProperty("subregion")
    private String subRegion;

    private Integer population;

    private List<String> timezones;

    private List<String> borders;

    private String cioc;

    private List<CurrencyDTO> currencies;

    private List<LanguageDTO> languages;

    private List<RegionalBlocDTO> regionalBlocs;

}