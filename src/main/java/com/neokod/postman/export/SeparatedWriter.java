package com.neokod.postman.export;

import com.neokod.postman.data.PostmanItem;
import com.neokod.postman.data.PostmanRequest;
import com.neokod.postman.data.PostmanResponse;
import com.neokod.postman.exception.PostmanRequestExportFailedException;
import com.neokod.postman.exception.PostmanResponseExportFailedException;
import com.neokod.postman.properties.PostmanExportProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static com.neokod.postman.constant.FileVariableConstants.SUCCESS_RESPONSE_NAME;

public class SeparatedWriter implements PostmanItemWriter {


    private final Logger LOGGER = LoggerFactory.getLogger(SeparatedWriter.class);

    private final PostmanResponseExporter postmanResponseExporter = new PostmanResponseExporter();

    private final PostmanRequestExporter postmanRequestExporter = new PostmanRequestExporter();

    private final PostmanFileNamingManager postmanFileNamingManager;

    private final PostmanExportProperties exportProperties;

    public SeparatedWriter(PostmanFileNamingManager postmanFileNamingManager,
                           PostmanExportProperties exportProperties) {
        this.postmanFileNamingManager = postmanFileNamingManager;
        this.exportProperties = exportProperties;
    }

    @Override
    public void write(String directoryPath, PostmanItem postmanItem) {
        if (postmanItem.getRequest() == null) return;

        PostmanRequest postmanRequest = postmanItem.getRequest();
        writeRequestToFile(directoryPath, SUCCESS_RESPONSE_NAME, postmanRequest);

        if (exportProperties.getResponseExportType().equals(PostmanExportProperties.SUCCESS_ONLY_EXPORT_VALUE))
            writeOnlySuccessResponse(directoryPath, postmanItem);
        else if (exportProperties.getResponseExportType().equals(PostmanExportProperties.ALL_EXPORT_VALUE))
            writeAllResponsesWithRequests(directoryPath, postmanItem);
    }

    private void writeRequestToFile(String directoryPath, String requestName, PostmanRequest postmanRequest) {
        String requestFileToWritePath = directoryPath + "/" + postmanFileNamingManager.createRequestName(postmanRequest, requestName);
        PrintWriter requestWriter;
        try {
            requestWriter = new PrintWriter(requestFileToWritePath);
            requestWriter.append(postmanRequestExporter.export(postmanRequest));
            requestWriter.close();
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage());
            throw new PostmanRequestExportFailedException(e.getMessage());
        }

    }

    private void writeResponseToFile(String directoryPath, PostmanResponse response) {
        String responseFileToWritePath = directoryPath + "/" + postmanFileNamingManager.createResponseName(response);
        try {
            PrintWriter printWriter = new PrintWriter(responseFileToWritePath);
            printWriter.append(postmanResponseExporter.export(response));
            printWriter.close();
        } catch (FileNotFoundException e) {
            throw new PostmanResponseExportFailedException("File not found: " + responseFileToWritePath);
        }

    }

    private void writeAllResponsesWithRequests(String directoryPath, PostmanItem requestItem) {
        for (PostmanResponse response : requestItem.getResponse()) {
            writeRequestToFile(directoryPath, response.getName(), response.getOriginalRequest());
            writeResponseToFile(directoryPath, response);

        }
    }

    private void writeOnlySuccessResponse(String directoryPath, PostmanItem item) {
        for (PostmanResponse response : item.getResponse()) {
            if (response.getName().equals(SUCCESS_RESPONSE_NAME)) {
                writeResponseToFile(directoryPath, response);
                break;
            }
        }
    }


//    public void writeResponse(String filePath, PostmanResponse postmanResponse) {
//        try {
//            if (postmanResponse == null) return;
//            PrintWriter writer = new PrintWriter(filePath);
//            writer.append(postmanResponse.absUrl().getRaw()).append("\n");
//            writer.append(postmanResponse.getOriginalRequest().getMethod()).append("\n");
//            writer.append(String.valueOf(postmanResponse.getCode())).append("\n\n");
//
//            if (postmanResponse.getBody() != null && postmanResponse.getBody() != null && !postmanResponse.getBody().equals("")) {
//                writer.append(REQUEST_BODY_PATTERN.replaceAll(REQUEST_REPLACE_NAME, postmanResponse.getBody()));
//            } else {
//                writer.append(EMPTY_REQUEST_BODY);
//            }
//
//            writer.close();
//        } catch (FileNotFoundException e) {
//            LOGGER.error("File not found: " + filePath);
//        }
//
//    }
}
