package com.neokod.postman.data;

import java.util.List;

public class PostmanRequestItem implements PostmanNamable{

    private String name;

    private PostmanRequest request;

    private List<PostmanResponse> response;

    private Object event;

    private Object protocolProfileBehavior;

    private List<PostmanRequestItem> item;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PostmanRequest getRequest() {
        return request;
    }

    public void setRequest(PostmanRequest request) {
        this.request = request;
    }

    public Object getProtocolProfileBehavior() {
        return protocolProfileBehavior;
    }

    public void setProtocolProfileBehavior(Object protocolProfileBehavior) {
        this.protocolProfileBehavior = protocolProfileBehavior;
    }

    public List<PostmanResponse> getResponse() {
        return response;
    }

    public void setResponse(List<PostmanResponse> response) {
        this.response = response;
    }

    public Object getEvent() {
        return event;
    }

    public void setEvent(Object event) {
        this.event = event;
    }

    public List<PostmanRequestItem> getItem() {
        return item;
    }

    public void setItem(List<PostmanRequestItem> item) {
        this.item = item;
    }
}
