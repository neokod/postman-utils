package com.neokod.postman.export.packetpart;

import com.neokod.postman.data.PostmanHttpPacket;
import com.neokod.postman.util.PostmanConversions;
import org.apache.commons.lang3.StringUtils;

import java.io.PrintWriter;
import java.util.Locale;

public class PayloadBodyExporter<T extends PostmanHttpPacket> implements PostmanHttpPacketPartExporter<T> {

    private final String partName;

    public PayloadBodyExporter(String partName) {
        this.partName = partName;
    }

    @Override
    public void exportPart(PrintWriter printWriter, T postmanHttpPacket) {
        printWriter.append(partName).append("\n");

        if (postmanHttpPacket != null && !StringUtils.isEmpty(postmanHttpPacket.bodyPayloadAsString().toLowerCase(Locale.ROOT)))
            printWriter.append(postmanHttpPacket.bodyPayloadAsString());
        else
            printWriter.append(PostmanConversions.emptyJsonBody());

    }
}
