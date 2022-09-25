package com.neokod.postman.filter;

import com.neokod.postman.data.PostmanItem;
import com.neokod.postman.data.PostmanResponse;
import com.neokod.postman.data.PostmanUrl;
import com.neokod.postman.data.PostmanVariable;
import org.springframework.util.CollectionUtils;

public class PostmanPostmanVariableReplace implements PostmanItemFilter {

    public PostmanPostmanVariableReplace() {
    }

    @Override
    public void filter(PostmanItem postmanItem) {
        if (!CollectionUtils.isEmpty(postmanItem.getResponse())) {
            for (PostmanResponse response : postmanItem.getResponse()) {
                if (response.getOriginalRequest() != null && response.getOriginalRequest().getUrl() != null)
                    formatUrl(response.getOriginalRequest().getUrl());
            }
        }
    }

    private void formatUrl(PostmanUrl postmanUrl) {
        if (!CollectionUtils.isEmpty(postmanUrl.getVariable())) {
            String formattedUrl = postmanUrl.getRaw().replaceAll(":", "");
            for (PostmanVariable variable : postmanUrl.getVariable()) {
                formattedUrl = formattedUrl.replaceAll(variable.getKey(), variable.getValue());
            }
            postmanUrl.setRaw(formattedUrl);
        }
    }
}
