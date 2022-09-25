package com.neokod.postman.export.packetpart;

import com.neokod.postman.data.PostmanHttpPacket;
import com.neokod.postman.util.PostmanConversions;

import java.io.PrintWriter;

public class PayloadBodyExporter<T extends PostmanHttpPacket> implements PostmanHttpPacketPartExporter<T> {

    private final String partName;

    public PayloadBodyExporter(String partName) {
        this.partName = partName;
    }

    @Override
    public void exportPart(PrintWriter printWriter, T postmanHttpPacket) {
        printWriter.append(partName).append("\n");

        if (postmanHttpPacket.hasBody())
            printWriter.append(postmanHttpPacket.bodyPayloadAsString());
        else
            printWriter.append(PostmanConversions.emptyJsonBody());

    }
}
