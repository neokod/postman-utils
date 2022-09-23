package com.neokod.postman.properties;

import com.neokod.postman.constant.PostmanUtilConstants;
import com.neokod.postman.enums.EnumExportDetailStrategy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = PostmanUtilConstants.EXPORT_PROPERTIES_PREFIX)
public class PostmanExportProperties {

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
