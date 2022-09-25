package com.neokod.postman.filter;

import com.neokod.postman.data.PostmanItem;
import com.neokod.postman.data.PostmanUrl;
import com.neokod.postman.env.PostmanEnvVariableManager;
import com.neokod.postman.util.PostmanFormatters;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PostmanUrlEnvVariableReplace implements PostmanItemFilter {

    private final PostmanEnvVariableManager variableManager;

    public PostmanUrlEnvVariableReplace(PostmanEnvVariableManager variableManager) {
        this.variableManager = variableManager;
    }

    @Override
    public void filter(PostmanItem requestItem) {
        PostmanFormatters.formatAllUrlPartsOfRequest(requestItem, this::formatUrl);

    }

    private void formatUrl(PostmanUrl postmanUrl) {
        if (!CollectionUtils.isEmpty(variableManager.keyValueMap())) {
            String formattedUrl = PostmanFormatters.cleanEnvVariablePattern(postmanUrl.getRaw());

            for (Map.Entry<String, String> entry : variableManager.keyValueMap().entrySet()) {
                formattedUrl = formattedUrl.replaceAll(entry.getKey(), entry.getValue());
            }
            List<String> formattedHostList = new ArrayList<>();
            for (String host : postmanUrl.getHost()) {
                String formattedHost = PostmanFormatters.cleanEnvVariablePattern(host);
                for (Map.Entry<String, String> entry : variableManager.keyValueMap().entrySet()) {
                    formattedHost = formattedHost.replaceAll(entry.getKey(), entry.getValue());
                }
                formattedHostList.add(formattedHost);
            }

            postmanUrl.setRaw(formattedUrl);
            postmanUrl.setHost(formattedHostList);
        }
    }
}
