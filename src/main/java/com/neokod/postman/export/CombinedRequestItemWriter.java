package com.neokod.postman.export;

import com.neokod.postman.data.PostmanItem;

/**
 * {@link com.neokod.postman.data.PostmanRequest} and {@link com.neokod.postman.data.PostmanResponse} are written in
 * separated files with a naming convention
 */
public class CombinedRequestItemWriter implements PostmanItemWriter {

    @Override
    public void write(String filePath, PostmanItem postmanItem) {
        if(postmanItem.getRequest() == null) return;

    }
}
