package com.fsm.facedetectionservice.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseMessage {
    private final UUID id = UUID.randomUUID();
    private String response;
    private String message;
    private Boolean success;
}
