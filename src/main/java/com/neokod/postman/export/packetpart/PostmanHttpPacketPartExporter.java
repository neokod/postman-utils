package com.neokod.postman.export.packetpart;

import com.neokod.postman.data.PostmanHttpPacket;

import java.io.PrintWriter;

public interface PostmanHttpPacketPartExporter<T extends PostmanHttpPacket> {

    void exportPart(PrintWriter writer, T postmanHttpPacket);
}
