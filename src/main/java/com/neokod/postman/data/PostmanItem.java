package com.neokod.postman.data;

import java.util.List;

public class PostmanItem implements PostmanNamable{

    private String name;

    private List<PostmanRequestItem> item;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PostmanRequestItem> getItem() {
        return item;
    }

    public void setItem(List<PostmanRequestItem> item) {
        this.item = item;
    }
}
