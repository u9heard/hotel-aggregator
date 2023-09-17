package org.zotov.hotel_aggregator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zotov.hotel_aggregator.credentials.UserCredentials;
import org.zotov.hotel_aggregator.exceptions.service.ModelNotFoundException;
import org.zotov.hotel_aggregator.exceptions.service.WrongCredentialsException;
import org.zotov.hotel_aggregator.interfaces.services.SecurityService;
import org.zotov.hotel_aggregator.models.Role;
import org.zotov.hotel_aggregator.models.User;
import org.zotov.hotel_aggregator.repositories.RoleRepository;
import org.zotov.hotel_aggregator.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService implements SecurityService<Long> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public User getById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new ModelNotFoundException(String.format("Model not found, id = %s", id)));
    }

    public Long authorize(UserCredentials credentials, String roleRequired){
        User checkUser = userRepository.getByUsername(credentials.getUsername()).orElseThrow(() -> new ModelNotFoundException("User with username = " + credentials.getUsername() + "not found"));
        if(!checkUser.getPassword().equals(credentials.getPassword())){
            throw new WrongCredentialsException("Invalid password for " + credentials.getUsername());
        }
        Role role = roleRepository.findById(checkUser.getRoleId()).orElseThrow(() -> new ModelNotFoundException("Role not found"));
        if(role.getRoleName().equals(roleRequired)) {
            return checkUser.getId();
        }
        throw new WrongCredentialsException(credentials.getUsername() + "does not have access");
    }

    public Long count(){
        return this.userRepository.count();
    }

    public String getRoleByUsername(String username){
        User searchUser = this.userRepository.getByUsername(username).orElseThrow(() -> new ModelNotFoundException(username + "not found"));
        return this.roleRepository.findById(searchUser.getRoleId()).orElseThrow(() -> new ModelNotFoundException("User " + username + "has an unknown role")).getRoleName();
    }
}
