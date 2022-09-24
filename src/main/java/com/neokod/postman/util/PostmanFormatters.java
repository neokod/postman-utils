package com.neokod.postman.util;

import com.neokod.postman.data.PostmanItem;
import com.neokod.postman.data.PostmanResponse;
import com.neokod.postman.data.PostmanUrl;
import org.springframework.util.CollectionUtils;

import static com.neokod.postman.constant.FileVariableConstants.*;

public class PostmanFormatters {

    public static void formatAllUrlPartsOfRequest(PostmanItem requestItem, PostmanUrlFormatter urlFormatter) {
        urlFormatter.format(requestItem.getRequest().getUrl());

        if (!CollectionUtils.isEmpty(requestItem.getResponse())) {
            for (PostmanResponse response : requestItem.getResponse()) {
                if (response.getUrl() != null)
                    urlFormatter.format(response.getUrl());
                if (response.getOriginalRequest() != null && response.getOriginalRequest().getUrl() != null)
                    urlFormatter.format(response.getOriginalRequest().getUrl());
            }
        }
    }

    public static String cleanEnvVariablePattern(String rawVariableValue) {
        return rawVariableValue.replaceAll(ENV_VARIABLE_PREFIX, "").replaceAll(ENV_VARIABLE_SUFFIX, "");
    }

    public interface PostmanUrlFormatter {
        void format(PostmanUrl postmanUrl);
    }


}
