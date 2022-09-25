package com.neokod.postman.env;

import com.neokod.postman.context.PostmanContextHolder;
import com.neokod.postman.data.PostmanEnvironment;
import com.neokod.postman.data.PostmanVariable;
import com.neokod.postman.properties.PostmanEnvironmentProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

@Component
public class PostmanEnvVariableManager {

    private final Map<String, String> postmanVariableKeyValueMap = new HashMap<>();

    private final PostmanEnvironmentProperties environmentProperties;

    @Autowired
    public PostmanEnvVariableManager(PostmanEnvironmentProperties environmentProperties) {
        this.environmentProperties = environmentProperties;
    }

    public void initialize() {
        final PostmanEnvironment environment = PostmanContextHolder.getContext().getEnvironment();
        if (!CollectionUtils.isEmpty(environment.getValues())) {
            for (PostmanVariable variable : environment.getValues()) {
                postmanVariableKeyValueMap.put(variable.getKey(), variable.getValue());
            }
        }
        if (!CollectionUtils.isEmpty(environmentProperties.getPostmanVariableList())) {
            for (PostmanVariable postmanVariable : environmentProperties.getPostmanVariableList()) {
                postmanVariableKeyValueMap.put(postmanVariable.getKey(), postmanVariable.getValue());
            }
        }
    }

    public Map<String, String> keyValueMap() {
        return postmanVariableKeyValueMap;
    }
}
