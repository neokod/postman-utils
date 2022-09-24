package com.neokod.postman.exception;

import com.neokod.postman.constant.PostmanUtilErrorCodes;

public class DirectoryCleaningIsFailedException extends PostmanUtilException {

    public DirectoryCleaningIsFailedException(String directoryPath) {
        super(PostmanUtilErrorCodes.DIRECTORY_CLEANING_IS_FAILED, directoryPath + " is not empty");
    }
}
