package com.neokod.postman.export.packetpart;

import com.neokod.postman.data.PostmanHttpPacket;
import com.neokod.postman.util.PostmanConversions;
import org.springframework.util.CollectionUtils;

import java.io.PrintWriter;

import static com.neokod.postman.constant.PostmanExportPatterns.HEADER_BODY_PART_NAME;

public class HeaderExporter<T extends PostmanHttpPacket> implements PostmanHttpPacketPartExporter<T> {

    @Override
    public void exportPart(PrintWriter writer, T postmanHttpPacket) {
        writer.append(HEADER_BODY_PART_NAME).append("\n");
        if (!CollectionUtils.isEmpty(postmanHttpPacket.headers())) {
            String headerJsonBody = PostmanConversions.toExportableJsonObject(postmanHttpPacket.headers());
            writer.append(headerJsonBody);
        } else {
            writer.append(PostmanConversions.emptyJsonBody());
        }
    }
}
