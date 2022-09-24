package com.neokod.postman.properties;

import com.neokod.postman.constant.FileVariableConstants;
import com.neokod.postman.constant.PostmanUtilConstants;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = PostmanUtilConstants.EXPORT_PROPERTIES_PREFIX)
public class PostmanExportProperties implements InitializingBean {

    public static final String SUCCESS_ONLY_EXPORT_VALUE = "success";

    public static final String ALL_EXPORT_VALUE = "all";

    private Boolean useResponseNameInFile;

    private String responseExportType;

    private String basePath;

    private String fileNameSeparator;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (useResponseNameInFile == null)
            useResponseNameInFile = false;
        if(responseExportType == null)
            responseExportType = SUCCESS_ONLY_EXPORT_VALUE;
        if(fileNameSeparator == null)
            fileNameSeparator = FileVariableConstants.DEFAULT_FILE_NAME_SEPARATOR;

    }

    public Boolean getUseResponseNameInFile() {
        return useResponseNameInFile;
    }

    public void setUseResponseNameInFile(Boolean useResponseNameInFile) {
        this.useResponseNameInFile = useResponseNameInFile;
    }

    public String getResponseExportType() {
        return responseExportType;
    }

    public void setResponseExportType(String responseExportType) {
        this.responseExportType = responseExportType;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getFileNameSeparator() {
        return fileNameSeparator;
    }

    public void setFileNameSeparator(String fileNameSeparator) {
        this.fileNameSeparator = fileNameSeparator;
    }
}
