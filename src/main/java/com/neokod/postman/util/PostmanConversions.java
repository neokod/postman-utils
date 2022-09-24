package com.neokod.postman.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neokod.postman.data.PostmanKeyValuePair;

import java.util.Collection;
import java.util.LinkedHashMap;

public class PostmanConversions {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T extends PostmanKeyValuePair> String toExportableJsonObject(Collection<T> keyValuePairCollection) throws JsonProcessingException {
        LinkedHashMap<String, String> valueObjectMap = new LinkedHashMap<>();
        for (PostmanKeyValuePair keyValuePair : keyValuePairCollection) {
            valueObjectMap.put(keyValuePair.getKey(), keyValuePair.getValue());
        }
        return OBJECT_MAPPER.writeValueAsString(valueObjectMap).replaceAll(",", ",\n");

    }
}
