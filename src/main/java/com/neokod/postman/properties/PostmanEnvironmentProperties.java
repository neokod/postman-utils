package com.neokod.postman.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "neokod.postman.environment")
public class PostmanEnvironmentProperties {

    private String filePath;

    private List<String> otherVariables;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<String> getOtherVariables() {
        return otherVariables;
    }

    public void setOtherVariables(List<String> otherVariables) {
        this.otherVariables = otherVariables;
    }
}
