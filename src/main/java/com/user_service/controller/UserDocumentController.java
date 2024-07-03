package com.user_service.controller;

import com.user_service.dto.UploadDocumentRequest;
import com.user_service.service.UserDocumentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "v1/documents")
@AllArgsConstructor
public class UserDocumentController {

    UserDocumentService userDocumentService;

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadDocuments(@Valid  UploadDocumentRequest uploadDocumentRequest){
        return ResponseEntity.ok(userDocumentService.uploadDocuments(uploadDocumentRequest));
    }
}
