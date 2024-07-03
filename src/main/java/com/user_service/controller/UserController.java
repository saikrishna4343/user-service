package com.user_service.controller;

import com.user_service.dto.CreateNewUserRequest;
import com.user_service.dto.UserRequest;
import com.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(path = "v1/user")
@AllArgsConstructor
public class UserController {

    UserService userService;

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewUser(@RequestBody @Valid CreateNewUserRequest createNewUserRequest){
        return ResponseEntity.ok(userService.addNewUser(createNewUserRequest));
    }

    @PutMapping(path = "/update/role", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUserRole(@RequestBody @Valid UserRequest userRequest){
        return ResponseEntity.ok(userService.updateUserRole(userRequest));
    }
}
