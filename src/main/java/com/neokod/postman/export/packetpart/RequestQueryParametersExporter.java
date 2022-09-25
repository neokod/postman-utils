package com.neokod.postman.export.packetpart;

import com.neokod.postman.data.PostmanRequest;
import com.neokod.postman.util.PostmanConversions;
import org.springframework.util.CollectionUtils;

import java.io.PrintWriter;

import static com.neokod.postman.constant.PostmanExportPatterns.QUERY_PARAM_BODY_PART_NAME;

public class RequestQueryParametersExporter implements PostmanHttpPacketPartExporter<PostmanRequest> {

    @Override
    public void exportPart(PrintWriter writer, PostmanRequest postmanRequest) {
        writer.append(QUERY_PARAM_BODY_PART_NAME).append("\n");
        if (!CollectionUtils.isEmpty(postmanRequest.getUrl().getQuery())) {
            String queryJsonBody = PostmanConversions.toExportableJsonObject(postmanRequest.getUrl().getQuery());
            writer.append(queryJsonBody);
        } else {
            writer.append(PostmanConversions.emptyJsonBody());
        }
    }
}
