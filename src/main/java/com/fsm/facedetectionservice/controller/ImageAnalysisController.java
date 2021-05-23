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
@RequestMapping("/analysis")
@AllArgsConstructor
//TODO ADD @CROSSORIGIN HERE IF NEEDED
public class ImageAnalysisController {

    private final MinioStorageService storageService;
    private final AnalysisService analysisService;

    @PostMapping("/initialize")
    public ResponseEntity<ResponseMessage> initialize(@RequestParam("namedPhotos") MultipartFile photos) {
        final var namedPhotosKey = storageService.save(photos);
        return analysisService.initialize(namedPhotosKey);
    }

    @PostMapping()
    public ResponseEntity<ResponseMessage> analyze(@RequestParam("targetPerson") MultipartFile targetPhoto,
                                                   @RequestParam("familyPhoto") MultipartFile familyPhoto) {
        final String targetImageName =
                storageService.save(targetPhoto);

        final String familyImageName =
                storageService.save(familyPhoto);

        final var result = analysisService
                .getAnalysisResult(targetImageName, familyImageName);

        return ResponseEntity.ok(result.getBody());
    }

}
