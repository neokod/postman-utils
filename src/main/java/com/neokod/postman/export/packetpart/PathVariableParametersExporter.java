package com.neokod.postman.export.packetpart;

import com.neokod.postman.data.PostmanRequest;
import com.neokod.postman.util.PostmanConversions;
import org.springframework.util.CollectionUtils;

import java.io.PrintWriter;

import static com.neokod.postman.constant.PostmanExportPatterns.PATH_VARIABLE_BODY_PART_NAME;

public class PathVariableParametersExporter implements PostmanHttpPacketPartExporter<PostmanRequest> {

    @Override
    public void exportPart(PrintWriter writer, PostmanRequest postmanRequest) {
        writer.append(PATH_VARIABLE_BODY_PART_NAME).append("\n");
        if (!CollectionUtils.isEmpty(postmanRequest.getUrl().getVariable())) {
            String variableJsonBody = PostmanConversions.toExportableJsonObject(postmanRequest.getUrl().getVariable());
            writer.append(variableJsonBody);
        } else {
            writer.append(PostmanConversions.emptyJsonBody());
        }
    }
}
