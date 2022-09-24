package com.neokod.postman.export;

import com.neokod.postman.data.PostmanResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostmanResponseExporter {

    public static final String RESPONSE_REPLACE_NAME = "__response__";

    public static final String RESPONSE_BODY_PATTERN = "RESPONSE\n" + RESPONSE_REPLACE_NAME + "\n\n";

    public static final String EMPTY_RESPONSE_BODY = "RESPONSE { } \n\n";


    private final Logger LOGGER = LoggerFactory.getLogger(SeparatedWriter.class);

    public String export(PostmanResponse postmanResponse) {
        if (postmanResponse == null) return EMPTY_RESPONSE_BODY;

        StringBuilder builder = new StringBuilder();
        builder.append(postmanResponse.absUrl().getRaw()).append("\n");
        builder.append(postmanResponse.getOriginalRequest().getMethod()).append("\n");
        builder.append(postmanResponse.getCode()).append("\n\n");

        if (postmanResponse.getBody() != null && !postmanResponse.getBody().equals("")) {
            builder.append(RESPONSE_BODY_PATTERN.replaceAll(RESPONSE_REPLACE_NAME, postmanResponse.getBody()));
        } else {
            builder.append(EMPTY_RESPONSE_BODY);
        }

        return builder.toString();
    }
}
