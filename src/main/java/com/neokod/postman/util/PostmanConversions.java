package com.neokod.postman.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neokod.postman.data.PostmanKeyValuePair;
import com.neokod.postman.exception.PostmanPartExportingFailedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.LinkedHashMap;

public class PostmanConversions {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T extends PostmanKeyValuePair> String toExportableJsonObject(Collection<T> keyValuePairCollection) {
        if (CollectionUtils.isEmpty(keyValuePairCollection)) return PostmanConversions.emptyJsonBody();

        LinkedHashMap<String, String> valueObjectMap = new LinkedHashMap<>();
        for (PostmanKeyValuePair keyValuePair : keyValuePairCollection) {
            if (!StringUtils.isEmpty(keyValuePair.getKey()) && !StringUtils.isEmpty(keyValuePair.getValue()))
                valueObjectMap.put(keyValuePair.getKey(), keyValuePair.getValue());
        }
        try {
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(valueObjectMap);
        } catch (JsonProcessingException e) {
            throw new PostmanPartExportingFailedException(e.getMessage());
        }
    }

    public  static String emptyJsonBody() {
        LinkedHashMap<String, String> valueObjectMap = new LinkedHashMap<>();
        try {
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(valueObjectMap);
        } catch (JsonProcessingException e) {
            throw new PostmanPartExportingFailedException(e.getMessage());
        }

    }
}
