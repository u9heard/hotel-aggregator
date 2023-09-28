package org.zotov.hotel_aggregator.services;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;
import org.zotov.hotel_aggregator.credentials.UserCredentials;
import org.zotov.hotel_aggregator.dto.user.UserRequestDTO;
import org.zotov.hotel_aggregator.dto.user.UserResponseDTO;
import org.zotov.hotel_aggregator.exceptions.service.*;
import org.zotov.hotel_aggregator.interfaces.services.ModelMapperService;
import org.zotov.hotel_aggregator.interfaces.services.AuthService;
import org.zotov.hotel_aggregator.interfaces.services.UserManagement;
import org.zotov.hotel_aggregator.models.Role;
import org.zotov.hotel_aggregator.models.User;
import org.zotov.hotel_aggregator.interfaces.repositories.RoleRepository;
import org.zotov.hotel_aggregator.interfaces.repositories.UserRepository;

import java.util.*;

@Service
public class UserService extends CrudService<UserResponseDTO, UserRequestDTO, User, UserRepository> implements UserManagement {

    RoleRepository roleRepository;

    public UserService(UserRepository repository, ModelMapperService<UserRequestDTO, UserResponseDTO, User> modelMapper, RoleRepository roleRepository) {
        super(repository, modelMapper, "User");
        this.roleRepository = roleRepository;
    }

    public UserResponseDTO save(UserRequestDTO user){
        if(checkUsernameExists(user.getUsername())){
            throw new ModelConflictException("Username already exists. Username = " + user.getUsername());
        }
        return super.save(user);
    }

    public void update(Long id, UserRequestDTO user){
        if(checkUsernameExists(user.getUsername())){
            throw new ModelConflictException("Username already exists. Username = " + user.getUsername());
        }
        Role userRole = this.roleRepository.findByRoleName(user.getRole()).orElseThrow(() -> new ModelNotFoundException("Role not exists"));

        this.repository.save(new User(id, user.getUsername(),user.getPassword(), userRole.getId()));
    }

    public Long count(){
        return this.repository.count();
    }

    public String getRoleByUsername(String username){
        User searchUser = this.repository.getByUsername(username).orElseThrow(() -> new ModelNotFoundException(username + "not found"));
        return this.roleRepository.findById(searchUser.getRoleId()).orElseThrow(() -> new ModelNotFoundException("User " + username + "has an unknown role")).getRoleName();
    }

    public boolean checkUsernameExists(String username){
        return this.repository.getByUsername(username).isPresent();
    }

    public List<UserResponseDTO> getAll() {
        return IterableUtils.toList(this.repository.findAll()).stream().map(this.modelMapper::modelToResponseDTO).toList();
    }
}
