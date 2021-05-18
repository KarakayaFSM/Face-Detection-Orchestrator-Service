package com.fsm.facedetectionservice.controller;

import com.fsm.facedetectionservice.data.model.ResponseMessage;
import com.fsm.facedetectionservice.service.AnalysisService;
import com.fsm.facedetectionservice.service.MinioStorageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/analyze")
@AllArgsConstructor
//TODO ADD @CROSSORIGIN HERE IF NEEDED
public class ImageAnalysisController {

    private final MinioStorageService storageService;
    private final AnalysisService analysisService;
    //private final RedisMessagePublisher<String, Object> messagePublisher;

    @PostMapping
    public ResponseEntity<ResponseMessage> upload(@RequestParam("file") MultipartFile file) {
        final String filename = file.getOriginalFilename();
        storageService.save(file, filename);
        final var result = analysisService.getAnalysisResult(filename);
        return ResponseEntity.ok(result.getBody());
    }


}
