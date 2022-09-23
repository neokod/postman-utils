package com.neokod.postman.export;

import com.neokod.postman.context.PostmanContextHolder;
import com.neokod.postman.data.PostmanCollection;
import com.neokod.postman.data.PostmanItem;
import com.neokod.postman.data.PostmanRequestItem;
import com.neokod.postman.data.PostmanResponse;
import com.neokod.postman.properties.PostmanExportProperties;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;

import static com.neokod.postman.constant.FileVariableConstants.SUCCESS_RESPONSE_NAME;

@Component
public class PostmanCollectionExporter {

    private final PostmanDirectoryManager directoryManager;

    private final PostmanFileNamingStrategy fileNamingStrategy;

    private final PostmanExportProperties exportProperties;

    private final PostmanItemRequestWriter postmanItemRequestWriter = new PostmanItemRequestWriter();

    private final Logger LOGGER = LoggerFactory.getLogger(PostmanCollectionExporter.class);

    @Autowired
    public PostmanCollectionExporter(PostmanDirectoryManager directoryManager,
                                     PostmanFileNamingStrategy fileNamingStrategy,
                                     PostmanExportProperties exportProperties) {
        this.directoryManager = directoryManager;
        this.fileNamingStrategy = fileNamingStrategy;
        this.exportProperties = exportProperties;
    }

    public void exportToPath() {
        PostmanCollection postmanCollection = PostmanContextHolder.getContext().getCollection();
        File baseFile = new File(exportProperties.getBasePath());
        if(!baseFile.exists()) {
            baseFile.mkdir();
        }
        try {
            FileUtils.cleanDirectory(baseFile);
        } catch (IOException ioException) {
            LOGGER.error("Cleaning base directory is failed: " + ioException.getMessage());
        }

        if (!CollectionUtils.isEmpty(postmanCollection.getItem())) {
            for (PostmanItem item : postmanCollection.getItem()) {
                try {
                    String directoryPath = directoryManager.getOrCreate(null, item);
                    if (!CollectionUtils.isEmpty(item.getItem())) {
                        for (PostmanRequestItem requestItem : item.getItem()) {
                            if (requestItem.getRequest() == null)
                                directoryPath = directoryManager.getOrCreate(directoryPath, item);
                            else {
                                String requestFileToWritePath = directoryPath + "/" + fileNamingStrategy.createRequestName(requestItem.getRequest(), SUCCESS_RESPONSE_NAME);
                                postmanItemRequestWriter.writeRequest(requestFileToWritePath, requestItem.getRequest());

                                if (!CollectionUtils.isEmpty(requestItem.getResponse())) {
                                    if (exportProperties.getResponseSelection().equals(PostmanExportProperties.EnumExportDetailStrategy.SUCCESS_ONLY))
                                        writeOnlySuccessResponse(directoryPath, requestItem);
                                    else if (exportProperties.getResponseSelection().equals(PostmanExportProperties.EnumExportDetailStrategy.ALL))
                                        writeAllResponsesWithRequests(directoryPath, requestItem);
                                }
                            }
                        }
                    }
                } catch (IOException ioException) {
                    LOGGER.error("Error on create : " + ioException.getMessage());
                }
            }
        }
    }

    private void writeAllResponsesWithRequests(String directoryPath, PostmanRequestItem requestItem) {
        for (PostmanResponse response : requestItem.getResponse()) {
            String requestFileToWritePath = directoryPath + "/" + fileNamingStrategy.createRequestName(response.getOriginalRequest(), response.getName());
            String responseFileToWritePath = directoryPath + "/" + fileNamingStrategy.createResponseName(response);

            postmanItemRequestWriter.writeRequest(requestFileToWritePath, response.getOriginalRequest());
            postmanItemRequestWriter.writeResponse(responseFileToWritePath, response);
        }
    }

    private void writeOnlySuccessResponse(String directoryPath, PostmanRequestItem requestItem) {
        for (PostmanResponse response : requestItem.getResponse()) {
            if (response.getName().equals(SUCCESS_RESPONSE_NAME)) {
                String responseFileToWritePath = directoryPath + "/" + fileNamingStrategy.createResponseName(response);
                postmanItemRequestWriter.writeResponse(responseFileToWritePath, response);
                break;
            }
        }
    }
}
