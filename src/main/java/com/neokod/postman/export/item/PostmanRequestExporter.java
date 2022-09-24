package com.neokod.postman.export.item;

import com.neokod.postman.data.PostmanRequest;
import com.neokod.postman.export.packetpart.*;
import com.neokod.postman.util.PostmanConversions;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static com.neokod.postman.constant.PostmanExportPatterns.*;

public class PostmanRequestExporter {

    private final List<PostmanHttpPacketPartExporter<PostmanRequest>> exportingChain = new ArrayList<>();

    {
        exportingChain.add(new HeaderExporter<>());
        exportingChain.add(new PayloadBodyExporter<>(REQUEST_BODY_PART_NAME));
        exportingChain.add(new RequestQueryParametersExporter());
        exportingChain.add(new PathVariableParametersExporter());
    }

    public void export(PrintWriter writer, PostmanRequest postmanRequest) {
        if (postmanRequest == null) writer.append(PostmanConversions.emptyJsonBody());
        writer.append(postmanRequest.getUrl().getRaw()).append("\n");
        writer.append(postmanRequest.getMethod()).append("\n\n");

        exportingChain.forEach(httpPacketPartExporter -> {
            httpPacketPartExporter.exportPart(writer, postmanRequest);
            writer.append("\n\n");
            writer.flush();
        });
    }
}
