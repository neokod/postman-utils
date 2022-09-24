package com.neokod.postman.export;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.neokod.postman.data.PostmanRequest;
import com.neokod.postman.exception.PostmanRequestExportFailedException;
import com.neokod.postman.util.PostmanConversions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import static com.neokod.postman.constant.PostmanExportPatterns.*;

public class PostmanRequestExporter {

    private final Logger LOGGER = LoggerFactory.getLogger(SeparatedWriter.class);

    public String export(PostmanRequest postmanRequest) {
        if (postmanRequest == null) return EMPTY_REQUEST_BODY;
        StringBuilder builder = new StringBuilder();

        builder.append(postmanRequest.getUrl().getRaw()).append("\n");
        builder.append(postmanRequest.getMethod()).append("\n\n");

        if (!CollectionUtils.isEmpty(postmanRequest.getHeader())) {
            try {
                String headerJsonBody = PostmanConversions.toExportableJsonObject(postmanRequest.getHeader());
                builder.append(HEADER_BODY_PART_NAME).append(headerJsonBody).append("\n\n");
            } catch (JsonProcessingException jsonProcessingException) {
                throw new PostmanRequestExportFailedException(jsonProcessingException.getOriginalMessage());
            }
        } else {
            builder.append(EMPTY_HEADER_BODY);
        }

        if (postmanRequest.getBody() != null && postmanRequest.getBody().getRaw() != null && !postmanRequest.getBody().getRaw().equals(""))
            builder.append(REQUEST_BODY_PART_NAME).append(postmanRequest.getBody().getRaw()).append("\n\n");

        if (!CollectionUtils.isEmpty(postmanRequest.getUrl().getQuery())) {
            try {
                String queryJsonBody = PostmanConversions.toExportableJsonObject(postmanRequest.getUrl().getQuery());
                builder.append(QUERY_PARAM_BODY_PART_NAME).append(queryJsonBody).append("\n\n");
            } catch (JsonProcessingException jsonProcessingException) {
                throw new PostmanRequestExportFailedException(jsonProcessingException.getOriginalMessage());
            }
        } else {
            builder.append(EMPTY_QUERY_PARAM_BODY);
        }
        return builder.toString();

    }
}
