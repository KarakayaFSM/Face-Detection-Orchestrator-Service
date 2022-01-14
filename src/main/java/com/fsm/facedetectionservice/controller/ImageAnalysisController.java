package com.fsm.facedetectionservice.controller;

import com.fsm.facedetectionservice.data.ResponseMessage;
import com.fsm.facedetectionservice.service.AnalysisService;
import com.fsm.facedetectionservice.service.MinioStorageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/analysis")
@AllArgsConstructor
public class ImageAnalysisController {

    private final MinioStorageService storageService;
    private final AnalysisService analysisService;

    @PostMapping("/initializeGroup")
    public ResponseEntity<ResponseMessage> initializeGroup(@RequestPart("namedPhotos") MultipartFile photos) {
        final var namedPhotosKey = storageService.save(photos);
        var result = analysisService.initializeGroup(namedPhotosKey);

        return ResponseEntity.ok(result.getBody());
    }

    @PostMapping("/group/listMembers")
    public ResponseEntity<ResponseMessage> listGroupMembers(@RequestParam("groupPhoto") MultipartFile groupPhoto) {
        final var groupPhotoKey = storageService.save(groupPhoto);
        var result = analysisService.listGroupMembers(groupPhotoKey);
        return ResponseEntity.ok(result.getBody());
    }

    @PostMapping("/searchPerson")
    public ResponseEntity<ResponseMessage> searchPerson(@RequestParam("targetPerson") MultipartFile targetPhoto,
                                                        @RequestParam("familyPhoto") MultipartFile familyPhoto) {
        final String targetImageName =
                storageService.save(targetPhoto);

        final String familyImageName =
                storageService.save(familyPhoto);

        final var result = analysisService
                .searchPerson(targetImageName, familyImageName);

        return ResponseEntity.ok(result.getBody());
    }

}
