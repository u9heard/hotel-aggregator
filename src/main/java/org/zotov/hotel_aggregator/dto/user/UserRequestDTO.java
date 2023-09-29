package org.zotov.hotel_aggregator.dto.user;


import jakarta.validation.constraints.NotBlank;


public class UserRequestDTO {

    @NotBlank(message = "{empty.user.username}")
    private String username;
    @NotBlank(message = "{empty.user.password}")
    private String password;
    @NotBlank(message = "{empty.user.role}")
    private String role;

    public UserRequestDTO() {
    }

    public UserRequestDTO(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
