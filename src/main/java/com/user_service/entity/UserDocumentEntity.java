package com.user_service.entity;

import com.user_service.enums.SupportingDocumentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_documents")
@Entity
public class UserDocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userAccountId;
    private String profilePicture;
    private String supportingDocument;
    private SupportingDocumentType supportingDocumentType;
    private ZonedDateTime createdDate;
    private ZonedDateTime updatedDate;
}
