package com.neokod.postman.filter;

import com.neokod.postman.data.*;
import com.neokod.postman.properties.PostmanRequestProperties;
import org.springframework.util.CollectionUtils;

public class RequestHeaderOverride implements PostmanItemFilter {

    private final PostmanRequestProperties requestProperties;

    public RequestHeaderOverride(PostmanRequestProperties requestProperties) {
        this.requestProperties = requestProperties;
    }

    @Override
    public void filter(PostmanItem postmanItem) {
        if (CollectionUtils.isEmpty(requestProperties.getHeaderVariableList())) return;
        if (postmanItem.getRequest() == null) return;
        replaceHeaders(postmanItem.getRequest());
        if (!CollectionUtils.isEmpty(postmanItem.getResponse())) {
            for (PostmanResponse response : postmanItem.getResponse()) {
                if (response.getOriginalRequest() != null)
                    replaceHeaders(response.getOriginalRequest());
            }
        }
    }

    private void replaceHeaders(PostmanRequest request) {
        if (!CollectionUtils.isEmpty(request.getHeader())) {
            boolean headerOverridden = false;
            for (PostmanVariable headerVariable : requestProperties.getHeaderVariableList()) {
                for (PostmanHeader header : request.getHeader()) {
                    if (header.getKey().equalsIgnoreCase(headerVariable.getKey())) {
                        header.setValue(headerVariable.getValue());
                        headerOverridden = true;
                    }
                }
                if (!headerOverridden) {
                    PostmanHeader newHeader = new PostmanHeader();
                    newHeader.setKey(headerVariable.getKey());
                    newHeader.setValue(headerVariable.getValue());
                    request.getHeader().add(newHeader);
                }
            }
        }
    }
}
