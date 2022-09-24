package com.neokod.postman.exception;

import com.neokod.postman.constant.PostmanUtilErrorCodes;

public class PostmanResponseExportFailedException extends PostmanUtilException{

    public PostmanResponseExportFailedException(String message) {
        super(PostmanUtilErrorCodes.POSTMAN_REQUEST_EXPORT_FAILED, message);
    }
}
