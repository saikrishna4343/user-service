package com.user_service.validator;

import com.user_service.dto.CreateNewUserRequest;
import com.user_service.dto.UserRequest;
import com.user_service.entity.UserEntity;
import com.user_service.enums.AccountStatus;
import com.user_service.enums.VerificationStatus;
import com.user_service.exception.UserException;
import com.user_service.repository.UserRepository;
import com.user_service.utils.UserExceptionConstant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserValidator {
    UserRepository userRepository;

    public void validateNewUser(CreateNewUserRequest createNewUserRequest){
        if(createNewUserRequest.getPassword().contains(createNewUserRequest.getFirstName()) || createNewUserRequest.getPassword().contains(createNewUserRequest.getLastName())) {
            throw new UserException(UserExceptionConstant.FIRST_LAST_NAME_IN_PASSWORD);
        }
        if(createNewUserRequest.getDateOfBirth() == null){
            throw new UserException(UserExceptionConstant.DATE_OF_BIRTH_CANNOT_BE_EMPTY);
        }
        userRepository.findByEmailOrPhone(createNewUserRequest.getEmail(), createNewUserRequest.getPhone()).ifPresent(userEntity ->
        {
            if(userEntity.getEmail().equalsIgnoreCase(createNewUserRequest.getEmail())){
                throw new UserException(String.format(UserExceptionConstant.USER_EXISTS_WITH_EMAIL, userEntity.getEmail()));
            }else{
                throw new UserException(String.format(UserExceptionConstant.USER_EXISTS_WITH_PHONE, userEntity.getPhone()));
            }
        });
    }

    public void validateUserForRoleUpdate(UserRequest userRequest) {
        UserEntity userEntity = userRepository.findByEmailOrUserAccountId(userRequest.getEmail(), userRequest.getUserAccountId()).orElseThrow(()->
                new UserException(String.format(UserExceptionConstant.USER_NOT_EXISTS_WITH_EMAIL, userRequest.getEmail())));
        validateActiveUser(userEntity);
        if(StringUtils.isEmpty(userRequest.getRole().name())){
            throw new UserException(UserExceptionConstant.PROVIDE_VALID_USER_ROLE);
        }
        if(StringUtils.isNotEmpty(userEntity.getRole().name()) && userEntity.getRole().name().equalsIgnoreCase(userRequest.getRole().name())){
            throw new UserException(String.format(UserExceptionConstant.USER_ALREADY_HAVE_GIVEN_ROLE, userRequest.getRole().name()));
        }
        if(StringUtils.isNotEmpty(userEntity.getAccountVerificationStatus().name()) && BooleanUtils.isNotTrue(userEntity.getAccountVerificationStatus().getDescription().equalsIgnoreCase(VerificationStatus.VERIFIED.getDescription()))){
            throw new UserException(UserExceptionConstant.ACCOUNT_NOT_VERIFIED);
        }
    }

    public void validateActiveUser(UserEntity user){
        if(StringUtils.isNotEmpty(user.getAccountStatus().name()) && user.getAccountStatus().getDescription().equalsIgnoreCase(AccountStatus.DEACTIVATED.getDescription())){
            throw new UserException(String.format(UserExceptionConstant.ACCOUNT_IS_DEACTIVATED, user.getEmail()));
        }
    }
}
