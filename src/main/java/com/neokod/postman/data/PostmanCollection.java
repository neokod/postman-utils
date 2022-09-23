package com.neokod.postman.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostmanCollection {

    private List<PostmanItem> item;

    public List<PostmanItem> getItem() {
        return item;
    }

    public void setItem(List<PostmanItem> item) {
        this.item = item;
    }
}
