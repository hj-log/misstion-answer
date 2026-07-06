package com.codeit.misstionanswer.dto.request;

public record BinaryContentCreateRequest (
        String fileName,
        String contentType,
        byte[] bytes
) {

}
