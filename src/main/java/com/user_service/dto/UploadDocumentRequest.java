package com.user_service.dto;

import com.user_service.enums.SupportingDocumentType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UploadDocumentRequest {
    @NotNull(message = "User Account Id cannot be null")
    private String userAccountId;
    private MultipartFile profilePicture;
    private MultipartFile supportedDocument;
    private SupportingDocumentType supportingDocumentType;
    private ZonedDateTime createdDate;
    private ZonedDateTime updatedDate;
    private String profilePictureKey;
    private String supportingDocumentKey;
}
