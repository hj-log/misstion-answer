package com.codeit.misstionanswer.entity;

import org.apache.commons.logging.*;

import java.time.*;
import java.util.*;

public class Channel {
    private UUID id;
    private Long createdAt;
    private Long updatedAt;

    private ChannelType type;
    private String name;
    private String description;

    public Channel(ChannelType type, String name, String description) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now() .getEpochSecond();
        this.type = type;
        this.name = name;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public Long getUpdatedAt(){
        return updatedAt;
    }

    public ChannelType getType(){
        return type;
    }

    public String getName(){
        return name;
    }

    public String getDescription() {
        return description;
    }

    // 기존 데이터와 비교해서, 실제로 변경된 값이 있을 때만 안전하게 데이터를 갱신하는 코드
    public void update(String newName, String newDescription) {
        // 업데이트 여부를 체크하는 플래그(기준) 변수를 false로 초기화
        // 아직 아무것도 안 바뀌었다 (false) 상태로 미리 정해둠.
        boolean anyValueUpdated = false;
        if(newName != null && !newName.equals(this.name)) {
            this.name = newName;
            // 실제로 데이터가 변경되었음을 알리기 위해 플래그 true로 전환
            anyValueUpdated = true;
        }

        if(newDescription != null && !newDescription.equals(this.description)) {
            this.description = newDescription;
            anyValueUpdated = true;
        }

        if(anyValueUpdated) {
            this.updatedAt = Instant.now().getEpochSecond();
        }
    }

}
