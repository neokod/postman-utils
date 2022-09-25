package com.neokod.postman.format;

import com.neokod.postman.data.PostmanItem;
import com.neokod.postman.env.PostmanEnvVariableManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostmanFormatChain implements PostmanItemFormat {

    private final PostmanEnvVariableManager variableManager;

    private final List<PostmanItemFormat> requestItemValidatorList = new ArrayList<>();

    @Autowired
    public PostmanFormatChain(PostmanEnvVariableManager variableManager) {
        this.variableManager = variableManager;
        initializeValidators();
    }

    private void initializeValidators() {
        requestItemValidatorList.add(new PostmanUrlEnvVariableReplace(variableManager));
        requestItemValidatorList.add(new PostmanHeaderEnvVariableReplace(variableManager));
        requestItemValidatorList.add(new PostmanPostmanVariableReplace());
    }


    @Override
    public void format(PostmanItem requestItem) {
        if(requestItem.getRequest() == null) return;

        if(!CollectionUtils.isEmpty(requestItemValidatorList)) {
            requestItemValidatorList.forEach(requestItemValidator -> requestItemValidator.format(requestItem));
        }
    }


}
