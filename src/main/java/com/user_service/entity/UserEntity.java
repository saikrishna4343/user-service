package com.user_service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.user_service.enums.AccountStatus;
import com.user_service.enums.Role;
import com.user_service.enums.VerificationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String userAccountId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Date dateOfBirth;
    private String password;
    private String domain;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
    @Enumerated(EnumType.STRING)
    private VerificationStatus accountVerificationStatus;
    private ZonedDateTime accountCreatedDate;
    private ZonedDateTime accountUpdatedDate;
}
