package com.neokod.postman.exception;

import com.neokod.postman.constant.PostmanUtilErrorCodes;

public class PostmanRequestExportFailedException extends PostmanUtilException{

    public PostmanRequestExportFailedException(String message) {
        super(PostmanUtilErrorCodes.POSTMAN_REQUEST_EXPORT_FAILED, message);
    }
}
