package com.neokod.postman.data;

import java.util.List;

public class PostmanAuth {

    private String type;

    private List<PostmanHeader> bearer;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<PostmanHeader> getBearer() {
        return bearer;
    }

    public void setBearer(List<PostmanHeader> bearer) {
        this.bearer = bearer;
    }
}
