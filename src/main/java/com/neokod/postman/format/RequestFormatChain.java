package com.neokod.postman.format;

import com.neokod.postman.data.PostmanRequestItem;
import com.neokod.postman.env.PostmanEnvVariableManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class RequestFormatChain implements RequestItemFormat {

    private final PostmanEnvVariableManager variableManager;

    private final List<RequestItemFormat> requestItemValidatorList = new ArrayList<>();

    @Autowired
    public RequestFormatChain(PostmanEnvVariableManager variableManager) {
        this.variableManager = variableManager;
        initializeValidators();
    }

    private void initializeValidators() {
        requestItemValidatorList.add(new PostmanUrlEnvVariableReplace(variableManager));
        requestItemValidatorList.add(new PostmanRequestVariableReplace());
        requestItemValidatorList.add(new PostmanHeaderEnvVariableReplace(variableManager));
    }


    @Override
    public void format(PostmanRequestItem requestItem) {
        if(requestItem.getRequest() == null) return;

        if(!CollectionUtils.isEmpty(requestItemValidatorList)) {
            requestItemValidatorList.forEach(requestItemValidator -> requestItemValidator.format(requestItem));
        }
    }


}
