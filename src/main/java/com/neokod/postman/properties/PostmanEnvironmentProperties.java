package com.neokod.postman.properties;

import com.neokod.postman.constant.PostmanUtilConstants;
import com.neokod.postman.data.PostmanKeyValuePair;
import com.neokod.postman.data.PostmanVariable;
import com.neokod.postman.util.PostmanProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.List;

@Component
@ConfigurationProperties(prefix = PostmanUtilConstants.ENV_PROPERTIES_PREFIX)
public class PostmanEnvironmentProperties implements InitializingBean {

    private String filePath;

    private List<String> variable;

    private List<PostmanVariable> postmanVariableList;

    @Override
    public void afterPropertiesSet() {
        if(!CollectionUtils.isEmpty(variable)) {
            postmanVariableList = PostmanProperties.parseStringListToVariableList(variable);
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<String> getVariable() {
        return variable;
    }

    public void setVariable(List<String> variable) {
        this.variable = variable;
    }

    public List<PostmanVariable> getPostmanVariableList() {
        return postmanVariableList;
    }
}
