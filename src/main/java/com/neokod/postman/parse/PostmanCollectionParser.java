package com.neokod.postman.parse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neokod.postman.data.PostmanCollection;
import com.neokod.postman.properties.PostmanParsingProperties;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class PostmanCollectionParser {

    private final PostmanParsingProperties properties;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Logger LOGGER = LoggerFactory.getLogger(PostmanCollectionParser.class);

    @Autowired
    public PostmanCollectionParser(PostmanParsingProperties properties) {
        this.properties = properties;
    }

    public PostmanCollection parse() {
        try {
            String jsonData = FileUtils.readFileToString(new File(properties.getFilePath()), StandardCharsets.UTF_8);
            final PostmanCollection postmanCollection = objectMapper.readValue(jsonData, PostmanCollection.class);
            LOGGER.info(postmanCollection.getItem().size() + " collection item is parsed");
            return postmanCollection;
        } catch (IOException ioException) {
            throw new RuntimeException("IO Read failed");
        }

    }
}
