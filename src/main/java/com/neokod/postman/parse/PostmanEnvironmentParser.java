package com.neokod.postman.parse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neokod.postman.data.PostmanEnvironment;
import com.neokod.postman.properties.PostmanEnvironmentProperties;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class PostmanEnvironmentParser {

    private final PostmanEnvironmentProperties properties;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Logger LOGGER = LoggerFactory.getLogger(PostmanEnvironmentParser.class);

    @Autowired
    public PostmanEnvironmentParser(PostmanEnvironmentProperties properties) {
        this.properties = properties;
    }

    public PostmanEnvironment parse() {
        try {
            String jsonData = FileUtils.readFileToString(new File(properties.getFilePath()), StandardCharsets.UTF_8);
            final PostmanEnvironment postmanEnvironment = objectMapper.readValue(jsonData, PostmanEnvironment.class);
            LOGGER.info(postmanEnvironment.getValues().size() + " environment item is parsed");
            return postmanEnvironment;
        } catch (IOException ioException) {
            throw new RuntimeException("IO Read failed");
        }

    }
}
