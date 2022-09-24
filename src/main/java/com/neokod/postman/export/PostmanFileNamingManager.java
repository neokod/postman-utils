package com.neokod.postman.export;

import com.neokod.postman.data.PostmanRequest;
import com.neokod.postman.data.PostmanResponse;
import com.neokod.postman.data.PostmanUrl;
import com.neokod.postman.properties.PostmanExportProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;

import static com.neokod.postman.constant.FileVariableConstants.*;

@Component
public class PostmanFileNamingManager {

    private final PostmanExportProperties exportProperties;

    @Autowired
    public PostmanFileNamingManager(PostmanExportProperties exportProperties) {
        this.exportProperties = exportProperties;
    }

    public String createRequestName(PostmanRequest postmanRequest, String responseName) {
        if (postmanRequest == null) return "";
        String formattedName = formattedNameFromUrl(postmanRequest.getUrl());
        if (responseName.equals(SUCCESS_RESPONSE_NAME))
            return formattedName + REQUEST_FILE_SUFFIX + JSON_EXT;
        else
            return (formattedName + exportProperties.getFileNameSeparator() +
                    responseName.toLowerCase(Locale.ROOT) + exportProperties.getFileNameSeparator() + JSON_EXT).replaceAll("-", exportProperties.getFileNameSeparator());
    }


    public String createResponseName(PostmanResponse postmanResponse) {
        if (postmanResponse == null) return "";
        String formattedName = formattedNameFromUrl(postmanResponse.absUrl());
        if (postmanResponse.getName().equals(SUCCESS_RESPONSE_NAME))
            return formattedName + RESPONSE_FILE_SUFFIX + JSON_EXT;
        else
            return (formattedName + exportProperties.getFileNameSeparator() +
                    postmanResponse.getName().toLowerCase(Locale.ROOT) + exportProperties.getFileNameSeparator() + JSON_EXT).replaceAll("-", exportProperties.getFileNameSeparator());

    }

    private String formattedNameFromUrl(PostmanUrl postmanUrl) {
        String formattedFileName = postmanUrl.combinedUrlFromPath();
        formattedFileName = formattedFileName.replaceAll(HTTPS_PREFIX, HTTPS_PREFIX_REPLACE);
        formattedFileName = formattedFileName.replaceAll(HTTP_PREFIX, HTTP_PREFIX_REPLACE);
        formattedFileName = formattedFileName.replaceAll(URL_SEPARATOR, exportProperties.getFileNameSeparator());
        return formattedFileName;
    }

}
