package org.zotov.hotel_aggregator.services.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zotov.hotel_aggregator.dto.user.UserRequestDTO;
import org.zotov.hotel_aggregator.dto.user.UserResponseDTO;
import org.zotov.hotel_aggregator.exceptions.service.InternalServiceException;
import org.zotov.hotel_aggregator.exceptions.service.ModelNotFoundException;
import org.zotov.hotel_aggregator.interfaces.services.ModelMapperService;
import org.zotov.hotel_aggregator.interfaces.repositories.RoleRepository;
import org.zotov.hotel_aggregator.models.User;

@Service
public class UserMapperService implements ModelMapperService<UserRequestDTO, UserResponseDTO, User> {
    private final RoleRepository roleRepository;

    @Autowired
    public UserMapperService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public User RequestDTOtoModel(UserRequestDTO userRequestDTO) {
        Long userRoleId = this.roleRepository.findByRoleName(userRequestDTO.getRole()).orElseThrow(() -> new ModelNotFoundException("User's role not found")).getId();
        return new User(null, userRequestDTO.getUsername(), userRequestDTO.getPassword(), userRoleId);
    }

    @Override
    public UserResponseDTO modelToResponseDTO(User user) {
        try {
            String roleName = this.roleRepository.findById(user.getRoleId()).orElseThrow(() -> new InternalServiceException("Can't find role for existing user")).getRoleName();
            return new UserResponseDTO(user.getId(), user.getUsername(), roleName);
        } catch (InternalServiceException e){
            e.printStackTrace();
            return new UserResponseDTO(user.getId(), user.getUsername(), "Unknown Role, id = " + user.getRoleId());
        }
    }
}
