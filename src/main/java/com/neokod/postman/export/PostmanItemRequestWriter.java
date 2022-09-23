package com.neokod.postman.export;

import com.neokod.postman.data.PostmanHeader;
import com.neokod.postman.data.PostmanRequest;
import com.neokod.postman.data.PostmanResponse;
import com.neokod.postman.data.PostmanVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PostmanItemRequestWriter {
    public static final String HEADER_REPLACE_NAME = "__header__";

    public static final String REQUEST_REPLACE_NAME = "__request__";

    public static final String QUERY_PARAM_REPLACE_NAME = "__query_param__";

    public static final String HEADER_BODY_PATTERN = "HEADER\n{\n" + HEADER_REPLACE_NAME + "} \n\n";

    public static final String REQUEST_BODY_PATTERN = "REQUEST\n" + REQUEST_REPLACE_NAME + "\n\n";

    public static final String QUERY_PARAM_PATTERN = "QUERY_PARAMETERS\n{\n " + QUERY_PARAM_REPLACE_NAME + "\n\n}";

    public static final String EMPTY_HEADER_BODY = "HEADER { }\n\n";

    public static final String EMPTY_REQUEST_BODY = "REQUEST { } \n\n";

    public static final String EMPTY_QUERY_PARAM_BODY = "QUERY_PARAMETERS { } \n\n";


    private final Logger LOGGER = LoggerFactory.getLogger(PostmanItemRequestWriter.class);

    public void writeRequest(String filePath, PostmanRequest postmanRequest) {
        try {
            if (postmanRequest == null) return;
            PrintWriter writer = new PrintWriter(filePath);
            writer.append(postmanRequest.getUrl().getRaw()).append("\n");
            writer.append(postmanRequest.getMethod()).append("\n\n");

            if (!CollectionUtils.isEmpty(postmanRequest.getHeader())) {
                StringBuilder headers = new StringBuilder();
                for (PostmanHeader header : postmanRequest.getHeader()) {
                    headers.append("\t\"").append(header.getKey()).append("\"").append(": ");
                    headers.append("\"").append(header.getValue()).append("\"").append("\n");
                }
                writer.append(HEADER_BODY_PATTERN.replaceAll(HEADER_REPLACE_NAME, headers.toString()));
            } else {
                writer.append(EMPTY_HEADER_BODY);
            }
            if (postmanRequest.getBody() != null && postmanRequest.getBody().getRaw() != null && !postmanRequest.getBody().getRaw().equals("")) {
                writer.append(REQUEST_BODY_PATTERN.replaceAll(REQUEST_REPLACE_NAME, postmanRequest.getBody().getRaw()));
            } else {
                writer.append(EMPTY_REQUEST_BODY);
            }

            if (!CollectionUtils.isEmpty(postmanRequest.getUrl().getQuery())) {
                StringBuilder queryParameters = new StringBuilder();
                for (PostmanVariable variable : postmanRequest.getUrl().getQuery()) {
                    queryParameters.append("\"").append(variable.getKey()).append("\"").append(": ");
                    queryParameters.append("\"").append(variable.getValue()).append("\"").append("\n");
                }
                writer.append(QUERY_PARAM_PATTERN.replaceAll(QUERY_PARAM_REPLACE_NAME, queryParameters.toString()));
            } else {
                writer.append(EMPTY_QUERY_PARAM_BODY);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found: " + filePath);
        }

    }

    public void writeResponse(String filePath, PostmanResponse postmanResponse) {
        try {
            if (postmanResponse == null) return;
            PrintWriter writer = new PrintWriter(filePath);
            writer.append(postmanResponse.absUrl().getRaw()).append("\n");
            writer.append(postmanResponse.getOriginalRequest().getMethod()).append("\n");
            writer.append(String.valueOf(postmanResponse.getCode())).append("\n\n");

            if (postmanResponse.getBody() != null && postmanResponse.getBody() != null && !postmanResponse.getBody().equals("")) {
                writer.append(REQUEST_BODY_PATTERN.replaceAll(REQUEST_REPLACE_NAME, postmanResponse.getBody()));
            } else {
                writer.append(EMPTY_REQUEST_BODY);
            }

            writer.close();
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found: " + filePath);
        }

    }
}
