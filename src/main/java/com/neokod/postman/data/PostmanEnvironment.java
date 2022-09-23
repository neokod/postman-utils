package com.neokod.postman.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostmanEnvironment {

    private String name;

    private List<PostmanVariable> values;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PostmanVariable> getValues() {
        return values;
    }

    public void setValues(List<PostmanVariable> values) {
        this.values = values;
    }
}
