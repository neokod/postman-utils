package com.neokod.postman.export.item;

import com.neokod.postman.data.PostmanResponse;
import com.neokod.postman.export.packetpart.HeaderExporter;
import com.neokod.postman.export.packetpart.PayloadBodyExporter;
import com.neokod.postman.export.packetpart.PostmanHttpPacketPartExporter;
import com.neokod.postman.properties.PostmanExportProperties;
import com.neokod.postman.util.PostmanConversions;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static com.neokod.postman.constant.PostmanExportPatterns.RESPONSE_BODY_PART_NAME;

public class PostmanResponseExporter {

    private final List<PostmanHttpPacketPartExporter<PostmanResponse>> exportingChain = new ArrayList<>();

    public PostmanResponseExporter(PostmanExportProperties exportProperties) {
        if (exportProperties.getExportResponseHeader())
            exportingChain.add(new HeaderExporter<>());
        exportingChain.add(new PayloadBodyExporter<>(RESPONSE_BODY_PART_NAME));
    }

    public void export(PrintWriter writer, PostmanResponse postmanResponse) {
        if (postmanResponse == null) writer.append(PostmanConversions.emptyJsonBody());

        writer.append(postmanResponse.absUrl().getRaw()).append("\n");
        writer.append(postmanResponse.method()).append("\n");
        writer.append(String.valueOf(postmanResponse.getCode())).append("\n\n");

        exportingChain.forEach(exporter -> {
            exporter.exportPart(writer, postmanResponse);
            writer.append("\n\n");
        });
    }
}
