package com.neokod.postman.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "neokod.postman.export")
public class PostmanExportProperties {

    public enum EnumExportDetailStrategy {SUCCESS_ONLY, ALL}

    private EnumExportDetailStrategy responseSelection;
    private boolean useResponseNameInFile;
    private String basePath;
    private String fileNameSeparator;

    public EnumExportDetailStrategy getResponseSelection() {
        return responseSelection;
    }

    public void setResponseSelection(EnumExportDetailStrategy responseSelection) {
        this.responseSelection = responseSelection;
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

    public boolean isUseResponseNameInFile() {
        return useResponseNameInFile;
    }

    public void setUseResponseNameInFile(boolean useResponseNameInFile) {
        this.useResponseNameInFile = useResponseNameInFile;
    }
}
