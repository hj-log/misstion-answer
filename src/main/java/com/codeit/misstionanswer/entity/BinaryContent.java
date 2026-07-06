package com.codeit.misstionanswer.entity;

import lombok.*;

import java.io.*;
import java.time.*;
import java.util.*;

@Getter
public class BinaryContent implements Serializable {
    private static final long serialvesionUID = 1L;

    private UUID id;
    private Instant createdAt;

    private String fileName;
    private Long size;
    private String contentType;
    private byte[] content;

    public BinaryContent(String fileName, Long size, String contentType, byte[] content) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();

        this.fileName = fileName;
        this.size = size;
        this.contentType = contentType;
        this.content = content;
    }


}
