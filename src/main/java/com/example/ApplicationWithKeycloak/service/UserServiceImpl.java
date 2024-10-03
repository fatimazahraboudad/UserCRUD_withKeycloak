package com.example.ApplicationWithKeycloak.service;

import com.example.ApplicationWithKeycloak.config.Credentials;
import com.example.ApplicationWithKeycloak.dto.UserDTO;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Value("${keycloak.realm}")
    public String realm;

    private Keycloak kaycloak;

    public UserServiceImpl(Keycloak kaycloak) {
        this.kaycloak = kaycloak;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        UserRepresentation user=new UserRepresentation();
        user.setEnabled(true);
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUserName());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmailVerified(true);

        CredentialRepresentation credential = Credentials.createPasswordCredentials(userDTO.getPassword());

        List<CredentialRepresentation> list =new ArrayList<>();
        list.add(credential);
        user.setCredentials(list);

        //RealmResource realm1= kaycloak.realm(realm);
        UsersResource usersResource = getUsersResource();

        Response response= usersResource.create(user);

        if (Objects.equals(201,response.getStatus())) {
            return userDTO;
        }
        return null;
    }

    private UsersResource getUsersResource() {
        RealmResource realm1= kaycloak.realm(realm);
        return realm1.users();
    }

    @Override
    public UserRepresentation getUserById(String userId) {
        return getUsersResource().get(userId).toRepresentation();
    }

    @Override
    public void deleteUserById(String userId) {
        getUsersResource().delete(userId);

    }
}
