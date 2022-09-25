package com.neokod.postman.util;

import com.neokod.postman.constant.PostmanUtilConstants;
import com.neokod.postman.data.PostmanVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class PostmanProperties {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostmanProperties.class);

    public static List<PostmanVariable> parseStringListToVariableList(List<String> variableStringList) {
        List<PostmanVariable> postmanVariableList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(variableStringList)) {
            for (String variableString : variableStringList) {
                String[] keyValuePair = variableString.split(PostmanUtilConstants.PROPERTIES_SEPARATOR);
                if (keyValuePair.length == 2) {
                    PostmanVariable postmanVariable = new PostmanVariable(keyValuePair[0].trim(), keyValuePair[1].trim());
                    postmanVariableList.add(postmanVariable);
                } else {
                    LOGGER.error("Malformed variable value: " + keyValuePair);
                }
            }
        }
        return postmanVariableList;
    }
}
