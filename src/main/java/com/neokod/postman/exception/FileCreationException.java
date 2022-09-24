package com.neokod.postman.exception;

import com.neokod.postman.constant.PostmanUtilErrorCodes;

public class FileCreationException extends PostmanUtilException {

    public FileCreationException(String message) {
        super(PostmanUtilErrorCodes.FILE_CREATION_IO_EXCEPTION, message);
    }
}
