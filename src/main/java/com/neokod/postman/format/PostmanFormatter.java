package com.neokod.postman.format;

import com.neokod.postman.context.PostmanContextHolder;
import com.neokod.postman.data.PostmanCollection;
import com.neokod.postman.data.PostmanItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class PostmanFormatter {

    private final RequestFormatChain formatChain;

    @Autowired
    public PostmanFormatter(RequestFormatChain formatChain) {
        this.formatChain = formatChain;
    }

    public void format() {
        PostmanCollection collection = PostmanContextHolder.getContext().getCollection();
        if(!CollectionUtils.isEmpty(collection.getItem())) {
            for (PostmanItem postmanItem : collection.getItem()) {
                if(!CollectionUtils.isEmpty(postmanItem.getItem())) {
                    for(PostmanItem requestItem : postmanItem.getItem()) {
                        formatChain.format(requestItem);
                    }
                }
            }
        }

    }
}
