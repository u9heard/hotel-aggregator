package org.zotov.hotel_aggregator.services.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.zotov.hotel_aggregator.credentials.UserCredentials;
import org.zotov.hotel_aggregator.exceptions.service.InternalServiceException;
import org.zotov.hotel_aggregator.exceptions.service.ModelNotFoundException;
import org.zotov.hotel_aggregator.exceptions.service.WrongCredentialsException;
import org.zotov.hotel_aggregator.interfaces.repositories.RoleRepository;
import org.zotov.hotel_aggregator.interfaces.repositories.UserRepository;
import org.zotov.hotel_aggregator.interfaces.services.AuthService;
import org.zotov.hotel_aggregator.interfaces.services.UserManagement;
import org.zotov.hotel_aggregator.models.Role;
import org.zotov.hotel_aggregator.models.User;

@Service
public class BasicAuthService implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public BasicAuthService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Long authorize(UserCredentials credentials, String roleRequired) {
        User checkUser = userRepository.getByUsername(credentials.getUsername()).orElseThrow(() -> new ModelNotFoundException("User with username = " + credentials.getUsername() + "not found"));
        if(!checkUser.getPassword().equals(credentials.getPassword())){
            throw new WrongCredentialsException("Invalid password for " + credentials.getUsername());
        }
        Role requiredRole = this.roleRepository.findByRoleName(roleRequired).orElseThrow(() -> new InternalServiceException("Role from controller annotation not found, role=" + roleRequired));
        Role userRole = roleRepository.findById(checkUser.getRoleId()).orElseThrow(() -> new ModelNotFoundException("Role not found"));
        if(userRole.getRoleLevel() >= requiredRole.getRoleLevel()) {
            return checkUser.getId();
        }
        throw new WrongCredentialsException(credentials.getUsername() + " does not have access");
    }
}
