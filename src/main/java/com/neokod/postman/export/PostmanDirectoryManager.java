package com.neokod.postman.export;

import com.neokod.postman.data.PostmanItemWithName;
import com.neokod.postman.exception.DirectoryCleaningIsFailedException;
import com.neokod.postman.exception.FileCreationException;
import com.neokod.postman.properties.PostmanExportProperties;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class PostmanDirectoryManager {

    private final PostmanExportProperties properties;

    @Autowired
    public PostmanDirectoryManager(PostmanExportProperties properties) {
        this.properties = properties;
    }

    public String getOrCreate(String rootPath, PostmanItemWithName item) {
        String newFilePath = item.getName().replaceAll("-", properties.getFileNameSeparator()).replaceAll("/", properties.getFileNameSeparator());
        if (rootPath == null)
            newFilePath = properties.getBasePath() + "/" + newFilePath;
        else
            newFilePath = rootPath + "/" + newFilePath;

        File directoryFile = new File(newFilePath);
        if (directoryFile.exists()) {
            if (!directoryFile.isDirectory())
                throw new FileCreationException(newFilePath + "  is not a directory");
        } else {
            directoryFile.mkdir();
            try {
                FileUtils.cleanDirectory(directoryFile);
            } catch (IOException ioException) {
                throw new DirectoryCleaningIsFailedException(directoryFile.getPath());
            }
        }
        return newFilePath;
    }
}
