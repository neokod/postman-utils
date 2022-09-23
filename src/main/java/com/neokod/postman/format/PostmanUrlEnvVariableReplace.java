package com.neokod.postman.format;

import com.neokod.postman.data.PostmanRequestItem;
import com.neokod.postman.data.PostmanUrl;
import com.neokod.postman.env.PostmanEnvVariableManager;
import com.neokod.postman.util.PostmanUrls;
import org.springframework.util.CollectionUtils;

import java.util.Map;

import static com.neokod.postman.constant.PostmanConstants.ENV_VARIABLE_PREFIX;
import static com.neokod.postman.constant.PostmanConstants.ENV_VARIABLE_SUFFIX;

public class PostmanUrlEnvVariableReplace implements RequestItemFormat {

    private final PostmanEnvVariableManager variableManager;

    public PostmanUrlEnvVariableReplace(PostmanEnvVariableManager variableManager) {
        this.variableManager = variableManager;
    }

    @Override
    public void format(PostmanRequestItem requestItem) {
        PostmanUrls.formatAllUrlPartsOfRequest(requestItem, this::formatUrl);

    }

    private void formatUrl(PostmanUrl postmanUrl) {
        if (!CollectionUtils.isEmpty(variableManager.keyValueMap())) {
            String formattedUrl = postmanUrl.getRaw();
            for (Map.Entry<String, String> entry: variableManager.keyValueMap().entrySet()) {
                formattedUrl = formattedUrl.replaceAll(ENV_VARIABLE_PREFIX + entry.getKey() + ENV_VARIABLE_SUFFIX, entry.getValue());
            }
            postmanUrl.setRaw(formattedUrl);
        }
    }
}
