package com.neokod.postman.filter;

import com.neokod.postman.context.PostmanContextHolder;
import com.neokod.postman.data.PostmanCollection;
import com.neokod.postman.data.PostmanItem;
import com.neokod.postman.env.PostmanEnvVariableManager;
import com.neokod.postman.properties.PostmanRequestProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostmanFilterChain {

    private final PostmanEnvVariableManager variableManager;

    private final PostmanRequestProperties requestProperties;

    private final List<PostmanItemFilter> itemFilterList = new ArrayList<>();

    @Autowired
    public PostmanFilterChain(PostmanEnvVariableManager variableManager,
                              PostmanRequestProperties requestProperties) {
        this.variableManager = variableManager;
        this.requestProperties = requestProperties;
        initializeValidators();
    }

    private void initializeValidators() {
        itemFilterList.add(new PostmanUrlEnvVariableReplace(variableManager));
        itemFilterList.add(new PostmanHeaderEnvVariableReplace(variableManager));
        itemFilterList.add(new PostmanPostmanVariableReplace());
        itemFilterList.add(new RequestHeaderOverride(requestProperties));
    }

    public void filterAll() {
        PostmanCollection collection = PostmanContextHolder.getContext().getCollection();
        if(!CollectionUtils.isEmpty(collection.getItem())) {
            for (PostmanItem postmanItem : collection.getItem()) {
                if(!CollectionUtils.isEmpty(postmanItem.getItem())) {
                    for(PostmanItem requestItem : postmanItem.getItem()) {
                        filterItem(requestItem);
                    }
                }
            }
        }

    }

    private void filterItem(PostmanItem requestItem) {
        if(requestItem.getRequest() == null) return;

        if(!CollectionUtils.isEmpty(itemFilterList)) {
            itemFilterList.forEach(requestItemValidator -> requestItemValidator.filter(requestItem));
        }
    }


}
