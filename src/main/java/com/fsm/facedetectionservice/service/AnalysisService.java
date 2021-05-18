package com.fsm.facedetectionservice.service;

import com.fsm.facedetectionservice.configuration.PythonConfig;
import com.fsm.facedetectionservice.data.model.ResponseMessage;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
@AllArgsConstructor
public class AnalysisService {

    @Bean
    private RestTemplate restTemplate() {
        return new RestTemplate();
    }

    private final PythonConfig pythonConfig;

    public ResponseEntity<ResponseMessage> getAnalysisResult(String fileName) {
        return restTemplate()
                .postForEntity(
                        pythonConfig.getServerUrl(),
                        null,
                        ResponseMessage.class,
                        Collections.singletonMap("filename", fileName)
                );
    }

}
