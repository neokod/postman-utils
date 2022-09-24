package com.neokod.postman.export;

import com.neokod.postman.context.PostmanContextHolder;
import com.neokod.postman.data.PostmanCollection;
import com.neokod.postman.data.PostmanItem;
import com.neokod.postman.exception.BaseDirectoryIsNotEmptyException;
import com.neokod.postman.export.item.PostmanItemWriter;
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
public class PostmanCollectionExporter {

    private final PostmanDirectoryManager directoryManager;

    private final PostmanExportProperties exportProperties;

    private final PostmanItemWriter postmanItemWriter;

    private final Logger LOGGER = LoggerFactory.getLogger(PostmanCollectionExporter.class);

    @Autowired
    public PostmanCollectionExporter(PostmanDirectoryManager directoryManager,
                                     PostmanExportProperties exportProperties,
                                     PostmanItemWriter postmanItemWriter) {
        this.directoryManager = directoryManager;
        this.exportProperties = exportProperties;
        this.postmanItemWriter = postmanItemWriter;
    }

    public void exportToPath() {
        PostmanCollection postmanCollection = PostmanContextHolder.getContext().getCollection();
        if (CollectionUtils.isEmpty(postmanCollection.getItem())) return;

        for (PostmanItem item : postmanCollection.getItem()) {
            exportItem(null, item);
        }
    }

    public void exportItem(String directoryPath, PostmanItem item) {
        String newDirectoryPath = directoryManager.getOrCreate(directoryPath, item);
        if (!CollectionUtils.isEmpty(item.getItem())) {
            for (PostmanItem subItem : item.getItem()) {
                if (!CollectionUtils.isEmpty(subItem.getItem()))
                    exportItem(newDirectoryPath, subItem);
                else if (subItem.getRequest() != null) {
                    postmanItemWriter.write(newDirectoryPath, subItem);
                }
            }
        }
    }
}
