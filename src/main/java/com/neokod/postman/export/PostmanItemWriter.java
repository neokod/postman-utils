package com.neokod.postman.export;

import com.neokod.postman.data.PostmanItem;

public interface PostmanItemWriter {

    void write(String filePath, PostmanItem requestItem);


}
