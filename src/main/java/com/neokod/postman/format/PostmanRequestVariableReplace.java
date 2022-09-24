package com.neokod.postman.format;

import com.neokod.postman.data.PostmanItem;
import com.neokod.postman.data.PostmanUrl;
import com.neokod.postman.data.PostmanVariable;
import com.neokod.postman.util.PostmanFormatters;
import org.springframework.util.CollectionUtils;

public class PostmanRequestVariableReplace implements RequestItemFormat {

    public PostmanRequestVariableReplace() {
    }

    @Override
    public void format(PostmanItem requestItem) {
        PostmanFormatters.formatAllUrlPartsOfRequest(requestItem, this::formatUrl);

    }

    private void formatUrl(PostmanUrl postmanUrl) {
        if (!CollectionUtils.isEmpty(postmanUrl.getVariable())) {
            String formattedUrl = postmanUrl.getRaw();
            for (PostmanVariable variable : postmanUrl.getVariable()) {
                formattedUrl = formattedUrl.replaceAll(variable.getKey(), variable.getValue());
            }
            postmanUrl.setRaw(formattedUrl);
        }
    }
}
