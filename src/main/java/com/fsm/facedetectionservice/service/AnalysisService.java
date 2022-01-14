package com.fsm.facedetectionservice.service;

import com.fsm.facedetectionservice.configuration.PythonConfig;
import com.fsm.facedetectionservice.data.ResponseMessage;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@AllArgsConstructor
public class AnalysisService {

    @Bean
    private RestTemplate restTemplate() {
        return new RestTemplate();
    }

    private final PythonConfig pythonConfig;

    public ResponseEntity<ResponseMessage> initializeGroup(String namedPhotosKey) {

        final var body = Map.of(
                "groupPhoto", namedPhotosKey
        );

        return doPost("/initializeGroup", body);
    }

    public ResponseEntity<ResponseMessage> listGroupMembers(String groupPhotoKey) {

        final var body = Map.of(
                "groupPhoto", groupPhotoKey
        );

        return doPost("/group/listMembers", body);
    }

    public ResponseEntity<ResponseMessage> searchPerson(String targetImageName, String familyImageName) {

        final var body = Map.of(
                "targetImageName", targetImageName,
                "familyImageName", familyImageName
        );

        return doPost("/searchPerson", body);
    }

    private ResponseEntity<ResponseMessage> doPost(String endPoint, Map<String, String> body) {
        return restTemplate()
                .postForEntity(
                        pythonConfig.getServerUrl() + endPoint,
                        body,
                        ResponseMessage.class
                );
    }
}
