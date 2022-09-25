package com.neokod.postman.context;

import com.neokod.postman.data.PostmanCollection;
import com.neokod.postman.data.PostmanEnvironment;
import com.neokod.postman.env.PostmanEnvVariableManager;
import com.neokod.postman.exception.BaseDirectoryIOException;
import com.neokod.postman.exception.BaseDirectoryIsNotEmptyException;
import com.neokod.postman.export.PostmanCollectionExporter;
import com.neokod.postman.filter.PostmanFilterChain;
import com.neokod.postman.parse.PostmanCollectionItemSelector;
import com.neokod.postman.parse.PostmanCollectionParser;
import com.neokod.postman.parse.PostmanEnvironmentParser;
import com.neokod.postman.properties.PostmanParsingProperties;
import com.neokod.postman.properties.PostmanExportProperties;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;

@Component
public class PostmanProcessRunner {

    private final PostmanCollectionParser postmanCollectionParser;

    private final PostmanCollectionItemSelector postmanCollectionItemSelector;

    private final PostmanEnvironmentParser postmanEnvironmentParser;

    private final PostmanFilterChain postmanFilterChain;

    private final PostmanCollectionExporter postmanCollectionExporter;

    private final PostmanEnvVariableManager postmanEnvVariableManager;

    private final PostmanParsingProperties collectionProperties;

    private final PostmanExportProperties exportProperties;

    private final Logger LOGGER = LoggerFactory.getLogger(PostmanProcessRunner.class);

    @Autowired
    public PostmanProcessRunner(PostmanCollectionParser postmanCollectionParser,
                                PostmanCollectionItemSelector postmanCollectionItemSelector,
                                PostmanEnvironmentParser postmanEnvironmentParser,
                                PostmanFilterChain postmanFilterChain,
                                PostmanCollectionExporter postmanCollectionExporter,
                                PostmanEnvVariableManager postmanEnvVariableManager,
                                PostmanParsingProperties collectionProperties,
                                PostmanExportProperties exportProperties) {
        this.postmanCollectionParser = postmanCollectionParser;
        this.postmanCollectionItemSelector = postmanCollectionItemSelector;
        this.postmanEnvironmentParser = postmanEnvironmentParser;
        this.postmanFilterChain = postmanFilterChain;
        this.postmanCollectionExporter = postmanCollectionExporter;
        this.postmanEnvVariableManager = postmanEnvVariableManager;
        this.collectionProperties = collectionProperties;
        this.exportProperties = exportProperties;
    }

    public void runProcess() {
        LOGGER.info("Postman parsing process is started. Collection file: " + collectionProperties.getFilePath());
        final PostmanCollection collection = postmanCollectionItemSelector.selectItems(postmanCollectionParser.parse());

        final PostmanEnvironment environment = postmanEnvironmentParser.parse();
        PostmanContextHolder.build(collection, environment);

        postmanEnvVariableManager.initialize();
        LOGGER.info("Postman Environment is initialized");

        if (CollectionUtils.isEmpty(collection.getItem())) {
            LOGGER.error("Postman collection is empty. Process is stopped...");
            return;
        }
        LOGGER.info("Postman Collection selected item count : " + collection.getItem().size());

        LOGGER.info("Postman collection items filtering process is started...");
        postmanFilterChain.filterAll();


        LOGGER.info("Postman collection writing process is started...Write path: " + exportProperties.getBasePath());
        File baseFile = new File(exportProperties.getBasePath());
        if (baseFile.exists()) {
            try {
                if (exportProperties.getBaseDirectoryExistPolicy().equals(PostmanExportProperties.FAIL_BASE_DIRECTORY_VALUE) &&
                        !FileUtils.isEmptyDirectory(baseFile)) {
                    throw new BaseDirectoryIsNotEmptyException();
                } else if (exportProperties.getBaseDirectoryExistPolicy().equals(PostmanExportProperties.DELETE_BASE_DIRECTORY_VALUE)) {
                    FileUtils.cleanDirectory(baseFile);
                }
            } catch (IOException ioException) {
                LOGGER.error("Base file creation is failed: " + ioException.getMessage());
                throw new BaseDirectoryIOException();
            }
        } else {
            baseFile.mkdir();
        }

        postmanCollectionExporter.exportToPath();

        LOGGER.info("DONE");
    }
}
