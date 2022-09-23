package com.neokod.postman.format;

import com.neokod.postman.data.PostmanHeader;
import com.neokod.postman.data.PostmanRequestItem;
import com.neokod.postman.data.PostmanUrl;
import com.neokod.postman.env.PostmanEnvVariableManager;
import org.springframework.util.CollectionUtils;

import java.util.Map;

import static com.neokod.postman.constant.FileVariableConstants.ENV_VARIABLE_PREFIX;
import static com.neokod.postman.constant.FileVariableConstants.ENV_VARIABLE_SUFFIX;

public class PostmanHeaderEnvVariableReplace implements RequestItemFormat {

    private final PostmanEnvVariableManager variableManager;

    public PostmanHeaderEnvVariableReplace(PostmanEnvVariableManager variableManager) {
        this.variableManager = variableManager;
    }

    @Override
    public void format(PostmanRequestItem requestItem) {
        if (CollectionUtils.isEmpty(variableManager.keyValueMap())) return;
        if (CollectionUtils.isEmpty(requestItem.getRequest().getHeader())) return;

        for (PostmanHeader header : requestItem.getRequest().getHeader()) {
            String newValue = header.getValue().replaceAll("\\{\\{", "").replaceAll("\\}\\}", "");
            if(newValue.equals(header.getValue())) continue;
            for (Map.Entry<String, String> entry : variableManager.keyValueMap().entrySet()) {
                newValue = newValue.replaceAll(entry.getKey(), entry.getValue());
                header.setValue(newValue);
            }
        }

    }

    private void formatUrl(PostmanUrl postmanUrl) {
        String formattedUrl = postmanUrl.getRaw();
        for (Map.Entry<String, String> entry : variableManager.keyValueMap().entrySet()) {
            formattedUrl = formattedUrl.replaceAll(ENV_VARIABLE_PREFIX + entry.getKey() + ENV_VARIABLE_SUFFIX, entry.getValue());
        }
        postmanUrl.setRaw(formattedUrl);
    }
}
