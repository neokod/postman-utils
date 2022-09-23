package com.neokod.postman.export;

import com.neokod.postman.data.PostmanItem;
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

    public String getOrCreate(String rootPath, PostmanItem item) throws IOException {
        String newFilePath;
        if (rootPath == null)
            newFilePath = properties.getBasePath() + "/" + item.getName().replaceAll("-", properties.getFileNameSeparator());
        else
            newFilePath = rootPath + "/" + item.getItem();

        File directoryFile = new File(newFilePath);
        if (directoryFile.exists()) {
            if (!directoryFile.isDirectory())
                throw new IOException(newFilePath + "  is not a directory");
        } else {
            directoryFile.mkdir();
            FileUtils.cleanDirectory(directoryFile);
        }
        return newFilePath;
    }
}
