package com.neokod.postman.data;

import java.util.List;

public class PostmanResponse {

    private String name;

    private PostmanRequest originalRequest;

    private PostmanUrl url;

    private List<PostmanHeader> header;

    private String status;

    private int code;

    private String body;

    private String _postman_previewlanguage;

    private Object cookie;

    public PostmanUrl absUrl() {
        return url != null ? url : originalRequest.getUrl();
    }

    public PostmanRequest getOriginalRequest() {
        return originalRequest;
    }

    public void setOriginalRequest(PostmanRequest originalRequest) {
        this.originalRequest = originalRequest;
    }

    public PostmanUrl getUrl() {
        return url;
    }

    public void setUrl(PostmanUrl url) {
        this.url = url;
    }

    public List<PostmanHeader> getHeader() {
        return header;
    }

    public void setHeader(List<PostmanHeader> header) {
        this.header = header;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String get_postman_previewlanguage() {
        return _postman_previewlanguage;
    }

    public void set_postman_previewlanguage(String _postman_previewlanguage) {
        this._postman_previewlanguage = _postman_previewlanguage;
    }

    public Object getCookie() {
        return cookie;
    }

    public void setCookie(Object cookie) {
        this.cookie = cookie;
    }
}
