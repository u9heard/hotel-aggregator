package org.zotov.hotel_aggregator.interfaces.services;

import org.zotov.hotel_aggregator.dto.user.UserRequestDTO;
import org.zotov.hotel_aggregator.dto.user.UserResponseDTO;

import java.util.List;

public interface UserManagement extends BaseService<UserResponseDTO, UserRequestDTO>{
    public Long count();
    public String getRoleByUsername(String username);
    public boolean checkUsernameExists(String username);
    public List<UserResponseDTO> getAll();
}
