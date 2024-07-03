package com.user_service.dto;

import com.user_service.enums.AccountStatus;
import com.user_service.enums.Role;
import com.user_service.enums.VerificationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    @NotNull(message = "Email cannot be empty")
    private String email;
    @NotNull(message = "Account Id cannot be empty")
    private String userAccountId;
    @NotNull(message = "Domain cannot be empty")
    private String domain;
    private Role role;
    private AccountStatus accountStatus;
    private VerificationStatus accountVerificationStatus;
}
