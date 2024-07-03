package com.user_service.service;

import com.sun.security.auth.UserPrincipal;
import com.user_service.dto.CreateNewUserRequest;
import com.user_service.dto.UserRequest;
import com.user_service.dto.UserResponse;
import com.user_service.entity.UserEntity;
import com.user_service.enums.AccountStatus;
import com.user_service.enums.Role;
import com.user_service.enums.VerificationStatus;
import com.user_service.exception.UserException;
import com.user_service.mapper.UserMapper;
import com.user_service.repository.UserRepository;
import com.user_service.utils.UserExceptionConstant;
import com.user_service.utils.UserUtils;
import com.user_service.utils.Utility;
import com.user_service.validator.UserValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserService implements UserDetailsService {

    UserRepository userRepository;
    UserValidator userValidator;

    Utility utility;

    public UserResponse addNewUser(CreateNewUserRequest createNewUserRequest) {
        try {
            userValidator.validateNewUser(createNewUserRequest);
            createNewUserRequest.setUserAccountId(UserUtils.generateAccountId.apply(createNewUserRequest.getDomain()));
            createNewUserRequest.setRole(Role.USER);
            createNewUserRequest.setAccountStatus(AccountStatus.ACTIVE);
            createNewUserRequest.setAccountVerificationStatus(VerificationStatus.NOT_VERIFIED);
            createNewUserRequest.setPassword(utility.bCryptPasswordEncoder().encode(createNewUserRequest.getPassword()));
            return UserMapper.userEntityUserResponseFunction
                    .apply(userRepository.save(UserMapper.userVoToUserEntity
                            .apply(createNewUserRequest))
                    );
        } catch (UserException userException) {
            throw userException;
        } catch (Exception exception) {
            throw new UserException(exception.getMessage());
        }
    }


    public UserResponse updateUserRole(UserRequest userRequest) {
        try {
            userValidator.validateUserForRoleUpdate(userRequest);
            Optional<UserEntity> optionalUserEntity = userRepository.findByEmailOrUserAccountId(userRequest.getEmail(), userRequest.getUserAccountId());
            if(optionalUserEntity.isPresent()){
                optionalUserEntity.get().setRole(userRequest.getRole());
                return UserMapper.userEntityUserResponseFunction.apply(userRepository.save(optionalUserEntity.get()));
            }else{
                throw new UserException(String.format(UserExceptionConstant.USER_NOT_EXISTS_WITH_EMAIL, userRequest.getEmail()));
            }
        } catch (UserException userException) {
            throw userException;
        } catch (Exception exception) {
            throw new UserException(exception.getMessage());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        try{
            UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(()-> new UserException(UserExceptionConstant.USER_NOT_EXISTS_WITH_EMAIL));
            return new User(userEntity.getEmail(), userEntity.getPassword(), new ArrayList<>());
        }catch (UserException userException){
            throw userException;
        } catch (Exception usernameNotFoundException){
            throw new UserException(ExceptionUtils.getMessage(usernameNotFoundException));
        }
    }

    public UserResponse getUser(String email) {
        try {
            Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(email);
            if(optionalUserEntity.isPresent()){
                return UserMapper.userEntityUserResponseFunction.apply(userRepository.save(optionalUserEntity.get()));
            }else{
                throw new UserException(String.format(UserExceptionConstant.USER_NOT_EXISTS_WITH_EMAIL, email));
            }
        } catch (UserException userException) {
            throw userException;
        } catch (Exception exception) {
            throw new UserException(exception.getMessage());
        }
    }
}
