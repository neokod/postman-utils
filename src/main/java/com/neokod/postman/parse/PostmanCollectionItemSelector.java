package com.neokod.postman.parse;

import com.neokod.postman.data.PostmanCollection;
import com.neokod.postman.data.PostmanItem;
import com.neokod.postman.properties.PostmanParsingProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostmanCollectionItemSelector {

    private final PostmanParsingProperties postmanParsingProperties;

    @Autowired
    public PostmanCollectionItemSelector(PostmanParsingProperties postmanParsingProperties) {
        this.postmanParsingProperties = postmanParsingProperties;
    }

    public PostmanCollection selectItems(PostmanCollection postmanCollection) {
        if (CollectionUtils.isEmpty(postmanParsingProperties.getSelectedItems())) return postmanCollection;

        if (CollectionUtils.isEmpty(postmanCollection.getItem())) return postmanCollection;
        List<PostmanItem> selectedItemList = new ArrayList<>();
        for (String selectedItemName : postmanParsingProperties.getSelectedItems()) {
            for (PostmanItem postmanItem : postmanCollection.getItem()) {
                if (postmanItem.getName().equals(selectedItemName))
                    selectedItemList.add(postmanItem);
            }
        }
        postmanCollection.setItem(selectedItemList);
        return postmanCollection;
    }
}
