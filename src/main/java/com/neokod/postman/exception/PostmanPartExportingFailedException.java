package com.neokod.postman.exception;

import com.neokod.postman.constant.PostmanUtilErrorCodes;

public class PostmanPartExportingFailedException extends PostmanUtilException {

    public PostmanPartExportingFailedException(String message) {
        super(PostmanUtilErrorCodes.POSTMAN_JSON_PARSING_FAILED, message);
    }
}
