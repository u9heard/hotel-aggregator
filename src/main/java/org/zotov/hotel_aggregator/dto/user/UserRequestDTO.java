package org.zotov.hotel_aggregator.dto.user;


import jakarta.validation.constraints.NotBlank;


public class UserRequestDTO {

    @NotBlank(message = "Username can't be empty")
    private String username;
    @NotBlank(message = "Password can't be empty")
    private String password;
    @NotBlank(message = "Role can't be empty")
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
