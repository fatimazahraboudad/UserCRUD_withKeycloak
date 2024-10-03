package com.example.ApplicationWithKeycloak.service;

import com.example.ApplicationWithKeycloak.config.Credentials;
import com.example.ApplicationWithKeycloak.config.KeycloakConfig;
import com.example.ApplicationWithKeycloak.dto.UserDTO;
import org.apache.catalina.User;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.Collections;


public interface UserService {


    UserDTO createUser(UserDTO userDTO);
    UserRepresentation getUserById(String userId);

    void deleteUserById(String userId);



//    public void addUser(UserDTO userDTO){
//        CredentialRepresentation credential = Credentials
//                .createPasswordCredentials(userDTO.getPassword());
//        UserRepresentation user = new UserRepresentation();
//        user.setUsername(userDTO.getUserName());
//        user.setFirstName(userDTO.getFirstName());
//        user.setLastName(userDTO.getLastName());
//        user.setEmail(userDTO.getEmail());
//        user.setCredentials(Collections.singletonList(credential));
//        user.setEnabled(true);
//
//        Keycloak keycloak  = KeycloakConfig.getInstance();
//        RealmResource realmResource = keycloak.realm(KeycloakConfig.realm);
//        UsersResource usersResource = realmResource.users();
//        usersResource.create(user);
//    }
}
