package com.user_service.service;

import com.user_service.dto.UploadDocumentRequest;
import com.user_service.entity.UserDocumentEntity;
import com.user_service.exception.UserException;
import com.user_service.repository.UserDocumentRepository;
import com.user_service.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.time.ZonedDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class UserDocumentService {

    UserDocumentRepository userDocumentRepository;
    UserRepository userRepository;
    S3Client s3Client;

    public String uploadDocuments(UploadDocumentRequest uploadDocumentRequest){
        try{
            userRepository.findByUserAccountId(uploadDocumentRequest.getUserAccountId()).orElseThrow(()-> new UserException("User Account Id not found"));
            if(userDocumentRepository.findByUserAccountId(uploadDocumentRequest.getUserAccountId()).isPresent()){
                throw new UserException("Document's already uploaded");
            }
            if(uploadDocumentRequest.getProfilePicture() != null){
                String key = uploadDocumentRequest.getProfilePicture().getOriginalFilename()+ZonedDateTime.now();
                PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                        .key(uploadDocumentRequest.getProfilePicture().getOriginalFilename()+key)
                        .bucket("profile-pictures")
                        .contentType("image/jpeg")
                        .build();
                s3Client.putObject(putObjectRequest, RequestBody.fromBytes(uploadDocumentRequest.getProfilePicture().getBytes()));
                uploadDocumentRequest.setProfilePictureKey(key);
            }
            if(uploadDocumentRequest.getSupportedDocument() != null){
                String key = uploadDocumentRequest.getProfilePicture().getOriginalFilename()+ZonedDateTime.now();
                PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                        .key(key)
                        .bucket("supporting-documents")
                        .build();
                s3Client.putObject(putObjectRequest, RequestBody.fromBytes(uploadDocumentRequest.getProfilePicture().getBytes()));
                uploadDocumentRequest.setSupportingDocumentKey(key);
            }
            userDocumentRepository.save(UserDocumentEntity.builder()
                    .userAccountId(uploadDocumentRequest.getUserAccountId())
                    .supportingDocument(uploadDocumentRequest.getSupportingDocumentKey())
                    .profilePicture(uploadDocumentRequest.getProfilePictureKey())
                    .supportingDocumentType(uploadDocumentRequest.getSupportingDocumentType())
                    .build());
            return "Uploaded Documents";
        } catch (UserException userException){
            throw userException;
        } catch (Exception e){
            log.error(ExceptionUtils.getMessage(e));
            throw new UserException("No able to upload documents");
        }
    }
}
