package com.user_service.dto;

import com.user_service.enums.AccountStatus;
import com.user_service.enums.Role;
import com.user_service.enums.VerificationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private String userAccountId;
    private String email;
    private String domain;
    private String role;
    private String accountStatus;
    private String accountVerificationStatus;
    private ZonedDateTime accountCreatedDate;
    private ZonedDateTime accountUpdatedDate;
}
