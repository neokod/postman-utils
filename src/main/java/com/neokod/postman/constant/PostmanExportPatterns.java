package com.neokod.postman.constant;

public class PostmanExportPatterns {

    public static final String HEADER_REPLACE_NAME = "__header__";

    public static final String REQUEST_REPLACE_NAME = "__request__";

    public static final String QUERY_PARAM_REPLACE_NAME = "__query_param__";

    public static final String EMPTY_BODY = "{ }";

    public static final String HEADER_BODY_PART_NAME = "HEADER";

    public static final String REQUEST_BODY_PART_NAME = "REQUEST";

    public static final String QUERY_PARAM_BODY_PART_NAME = "QUERY_PARAMETERS";

    public static final String EMPTY_HEADER_BODY = HEADER_BODY_PART_NAME + EMPTY_BODY;

    public static final String EMPTY_REQUEST_BODY = REQUEST_BODY_PART_NAME + EMPTY_BODY;

    public static final String EMPTY_QUERY_PARAM_BODY = QUERY_PARAM_BODY_PART_NAME + EMPTY_BODY;
}
