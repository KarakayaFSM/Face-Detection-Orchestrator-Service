package com.fsm.facedetectionservice.data.model;

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
    String response;
    String message;
    Boolean success;
}
