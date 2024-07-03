package com.user_service.mapper;

import com.user_service.dto.CreateNewUserRequest;
import com.user_service.dto.UserResponse;
import com.user_service.entity.UserEntity;

import java.util.function.Function;

public class UserMapper {

    public static Function<CreateNewUserRequest, UserEntity> userVoToUserEntity = createNewUserRequest ->
            UserEntity.builder()
                    .userAccountId(createNewUserRequest.getUserAccountId())
                    .firstName(createNewUserRequest.getFirstName())
                    .lastName(createNewUserRequest.getLastName())
                    .email(createNewUserRequest.getEmail())
                    .phone(createNewUserRequest.getPhone())
                    .dateOfBirth(createNewUserRequest.getDateOfBirth())
                    .password(createNewUserRequest.getPassword())
                    .domain(createNewUserRequest.getDomain())
                    .role(createNewUserRequest.getRole())
                    .accountStatus(createNewUserRequest.getAccountStatus())
                    .accountVerificationStatus(createNewUserRequest.getAccountVerificationStatus())
                    .accountCreatedDate(createNewUserRequest.getAccountCreatedDate())
                    .accountUpdatedDate(createNewUserRequest.getAccountUpdatedDate())
                    .build();

    public static Function<UserEntity, UserResponse> userEntityUserResponseFunction = userEntity ->
            UserResponse.builder()
                    .email(userEntity.getEmail())
                    .userAccountId(userEntity.getUserAccountId())
                    .role(userEntity.getRole().name())
                    .domain(userEntity.getDomain())
                    .accountStatus(userEntity.getAccountStatus().getDescription())
                    .accountVerificationStatus(userEntity.getAccountVerificationStatus().getDescription())
                    .accountCreatedDate(userEntity.getAccountCreatedDate())
                    .accountUpdatedDate(userEntity.getAccountUpdatedDate()).
                    build();
}
