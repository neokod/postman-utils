package com.neokod.postman.properties;

import com.neokod.postman.constant.PostmanUtilConstants;
import com.neokod.postman.data.PostmanVariable;
import com.neokod.postman.util.PostmanProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
@ConfigurationProperties(prefix = PostmanUtilConstants.REQUEST_PROPERTIES_PREFIX)
public class PostmanRequestProperties implements InitializingBean {

    private List<String> header;

    private List<PostmanVariable> headerVariableList;

    @Override
    public void afterPropertiesSet() throws Exception {
        if(!CollectionUtils.isEmpty(header)) {
            headerVariableList = PostmanProperties.parseStringListToVariableList(header);
        }
    }

    public List<String> getHeader() {
        return header;
    }

    public void setHeader(List<String> header) {
        this.header = header;
    }

    public List<PostmanVariable> getHeaderVariableList() {
        return headerVariableList;
    }
}
