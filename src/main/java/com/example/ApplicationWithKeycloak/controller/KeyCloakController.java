package com.example.ApplicationWithKeycloak.controller;

import com.example.ApplicationWithKeycloak.dto.UserDTO;
import com.example.ApplicationWithKeycloak.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class KeyCloakController {

    private final UserService userService;

    @PostMapping
    public UserDTO addUser(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }

    @GetMapping
    public UserRepresentation getUserById(Principal principal){
        return userService.getUserById(principal.getName());
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable String userId){
        userService.deleteUserById(userId);

    }
}
