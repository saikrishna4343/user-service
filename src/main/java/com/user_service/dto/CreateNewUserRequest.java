package com.user_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.user_service.enums.AccountStatus;
import com.user_service.enums.Role;
import com.user_service.enums.VerificationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateNewUserRequest {
    private String userAccountId;
    @NotNull(message = "First Name cannot be empty")
    private String firstName;
    @NotNull(message = "Last Name cannot be empty")
    private String lastName;
    @NotNull(message = "Email cannot be empty")
    private String email;
    @NotNull(message = "Phone Number cannot be empty")
    private String phone;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private Date dateOfBirth;
    @NotNull(message = "Password cannot be empty")
    private String password;
    @NotNull(message = "Domain cannot be empty")
    private String domain;
    private Role role;
    private AccountStatus accountStatus;
    private VerificationStatus accountVerificationStatus;
    private ZonedDateTime accountCreatedDate;
    private ZonedDateTime accountUpdatedDate;
}
