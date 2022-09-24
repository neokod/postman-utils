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

    public static final String DELETE_BASE_DIRECTORY_VALUE = "delete";

    public static final String FAIL_BASE_DIRECTORY_VALUE = "fail";

    private String responseExportType;

    private String basePath;

    private String fileNameSeparator;

    private Boolean exportResponseHeader;

    private String baseDirectoryExistPolicy;

    @Override
    public void afterPropertiesSet() {
        if (responseExportType == null)
            responseExportType = SUCCESS_ONLY_EXPORT_VALUE;
        if (fileNameSeparator == null)
            fileNameSeparator = FileVariableConstants.DEFAULT_FILE_NAME_SEPARATOR;
        if (exportResponseHeader == null)
            exportResponseHeader = false;
        if (baseDirectoryExistPolicy == null)
            baseDirectoryExistPolicy = FAIL_BASE_DIRECTORY_VALUE;
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

    public Boolean getExportResponseHeader() {
        return exportResponseHeader;
    }

    public void setExportResponseHeader(Boolean exportResponseHeader) {
        this.exportResponseHeader = exportResponseHeader;
    }

    public String getBaseDirectoryExistPolicy() {
        return baseDirectoryExistPolicy;
    }

    public void setBaseDirectoryExistPolicy(String baseDirectoryExistPolicy) {
        this.baseDirectoryExistPolicy = baseDirectoryExistPolicy;
    }
}
