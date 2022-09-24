package com.neokod.postman.properties;

import com.neokod.postman.constant.PostmanUtilConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = PostmanUtilConstants.PARSE_PROPERTIES_PREFIX)
public class PostmanParsingProperties {

    private String filePath;

    private List<String> selectedItems;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<String> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<String> selectedItems) {
        this.selectedItems = selectedItems;
    }
}
