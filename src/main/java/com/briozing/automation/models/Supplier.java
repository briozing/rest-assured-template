package com.briozing.automation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {

    @JsonProperty("id")
    private String id;

    @JsonProperty("supplier-id")
    private String supplierId;

    @JsonProperty("type")
    private String type;

    @JsonProperty("name")
    private String organisation;

    @JsonProperty("primary-contact")
    private String primaryContact;

    @JsonProperty("project-manager")
    private String projectManager;

}