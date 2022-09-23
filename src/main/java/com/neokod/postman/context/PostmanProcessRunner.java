package com.neokod.postman.context;

import com.neokod.postman.data.PostmanCollection;
import com.neokod.postman.data.PostmanEnvironment;
import com.neokod.postman.env.PostmanEnvVariableManager;
import com.neokod.postman.export.PostmanCollectionExporter;
import com.neokod.postman.format.PostmanFormatter;
import com.neokod.postman.parse.PostmanCollectionParser;
import com.neokod.postman.parse.PostmanEnvironmentParser;
import com.neokod.postman.properties.PostmanParsingProperties;
import com.neokod.postman.properties.PostmanExportProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.File;

@Component
public class PostmanProcessRunner {

    private final PostmanCollectionParser postmanCollectionParser;

    private final PostmanEnvironmentParser postmanEnvironmentParser;

    private final PostmanFormatter postmanFormatter;

    private final PostmanCollectionExporter postmanCollectionExporter;

    private final PostmanEnvVariableManager postmanEnvVariableManager;

    private final PostmanParsingProperties collectionProperties;

    private final PostmanExportProperties exportProperties;

    private final Logger LOGGER = LoggerFactory.getLogger(PostmanProcessRunner.class);

    @Autowired
    public PostmanProcessRunner(PostmanCollectionParser postmanCollectionParser,
                                PostmanEnvironmentParser postmanEnvironmentParser,
                                PostmanFormatter postmanFormatter,
                                PostmanCollectionExporter postmanCollectionExporter,
                                PostmanEnvVariableManager postmanEnvVariableManager,
                                PostmanParsingProperties collectionProperties,
                                PostmanExportProperties exportProperties) {
        this.postmanCollectionParser = postmanCollectionParser;
        this.postmanEnvironmentParser = postmanEnvironmentParser;
        this.postmanFormatter = postmanFormatter;
        this.postmanCollectionExporter = postmanCollectionExporter;
        this.postmanEnvVariableManager = postmanEnvVariableManager;
        this.collectionProperties = collectionProperties;
        this.exportProperties = exportProperties;
    }

    public void runProcess() {
        LOGGER.info("Postman parsing process is started. Collection file: " + collectionProperties.getFilePath());

        LOGGER.info("Postman parsing process is started. Collection file: " + collectionProperties.getFilePath());
        final PostmanCollection collection = postmanCollectionParser.parse();
        final PostmanEnvironment environment = postmanEnvironmentParser.parse();
        PostmanContextHolder.build(collection, environment);

        postmanEnvVariableManager.initialize();
        LOGGER.info("Postman Environment is initialized");

        if(CollectionUtils.isEmpty(collection.getItem())) {
            LOGGER.error("Postman collection is empty. Process is stopped...");
            return;
        }
        LOGGER.info("Postman Collection item count : " + collection.getItem().size());

        LOGGER.info("Postman re-formatting process is started...");
        postmanFormatter.format();

        LOGGER.info("Postman collection writing process is started...Write path: " + exportProperties.getBasePath());
        postmanCollectionExporter.exportToPath();

        LOGGER.info("DONE");


    }
}
