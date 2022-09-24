package com.neokod.postman.exception;

import com.neokod.postman.constant.PostmanUtilErrorCodes;

public class BaseDirectoryIOException extends PostmanUtilException {

    public BaseDirectoryIOException() {
        super(PostmanUtilErrorCodes.BASE_DIRECTORY_IS_NOT_EMPTY, "Base directory is not empty");
    }
}
