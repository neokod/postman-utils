package com.neokod.postman.data;

import java.util.List;

public interface PostmanHttpPacket {

    String method();

    List<PostmanHeader> headers();

    String bodyPayloadAsString();

    Boolean hasBody();
}
