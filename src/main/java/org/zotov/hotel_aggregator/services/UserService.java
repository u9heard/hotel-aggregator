package org.zotov.hotel_aggregator.services;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public UserResponseDTO save(UserRequestDTO user){
        if(checkUsernameExists(user.getUsername())){
            throw new ModelConflictException("Username already exists. Username = " + user.getUsername());
        }
        return super.save(user);
    }

    @Transactional
    public void update(Long id, UserRequestDTO user){
        if(!checkUserExists(id, user.getUsername())){
            throw new ModelConflictException("Unable to edit username " + user.getUsername());
        }
        User userToUpdate = this.modelMapper.RequestDTOtoModel(user);
        userToUpdate.setId(id);
        this.repository.save(userToUpdate);
    }

    @Transactional(readOnly = true)
    public String getRoleByUsername(String username){
        User searchUser = this.repository.getByUsername(username).orElseThrow(() -> new ModelNotFoundException(username + "not found"));
        return this.roleRepository.findById(searchUser.getRoleId()).orElseThrow(() -> new ModelNotFoundException("User " + username + "has an unknown role")).getRoleName();
    }

    @Transactional
    public List<UserResponseDTO> getAll() {
        return IterableUtils.toList(this.repository.findAll()).stream().map(this.modelMapper::modelToResponseDTO).toList();
    }

    private boolean checkUsernameExists(String username){
        return this.repository.getByUsername(username).isPresent();
    }

    private boolean checkUserExists(Long id, String username){
        User user = this.repository.findById(id).orElseThrow(() -> new ModelNotFoundException("User not found"));
        return user.getUsername().equals(username);
    }
}
