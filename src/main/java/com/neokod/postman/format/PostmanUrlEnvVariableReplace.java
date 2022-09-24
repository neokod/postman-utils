package com.neokod.postman.format;

import com.neokod.postman.data.PostmanItem;
import com.neokod.postman.data.PostmanUrl;
import com.neokod.postman.env.PostmanEnvVariableManager;
import com.neokod.postman.util.PostmanFormatters;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.neokod.postman.constant.FileVariableConstants.ENV_VARIABLE_PREFIX;
import static com.neokod.postman.constant.FileVariableConstants.ENV_VARIABLE_SUFFIX;

public class PostmanUrlEnvVariableReplace implements RequestItemFormat {

    private final PostmanEnvVariableManager variableManager;

    public PostmanUrlEnvVariableReplace(PostmanEnvVariableManager variableManager) {
        this.variableManager = variableManager;
    }

    @Override
    public void format(PostmanItem requestItem) {
        PostmanFormatters.formatAllUrlPartsOfRequest(requestItem, this::formatUrl);

    }

    private void formatUrl(PostmanUrl postmanUrl) {
        if (!CollectionUtils.isEmpty(variableManager.keyValueMap())) {
            String formattedUrl = postmanUrl.getRaw();

            for (Map.Entry<String, String> entry : variableManager.keyValueMap().entrySet()) {
                formattedUrl = formattedUrl.replaceAll(ENV_VARIABLE_PREFIX + entry.getKey() + ENV_VARIABLE_SUFFIX, entry.getValue());
            }
            List<String> formattedHostList = new ArrayList<>();
            for (String host : postmanUrl.getHost()) {
                for (Map.Entry<String, String> entry : variableManager.keyValueMap().entrySet()) {
                    if (host.contains(ENV_VARIABLE_PREFIX + entry.getKey() + ENV_VARIABLE_SUFFIX)) {
                        formattedHostList.add(host.replaceAll(ENV_VARIABLE_PREFIX + entry.getKey() + ENV_VARIABLE_SUFFIX, entry.getValue()));
                    } else {
                        formattedHostList.add(host);
                    }
                }
            }

            postmanUrl.setRaw(formattedUrl);
            postmanUrl.setHost(formattedHostList);
        }
    }
}
