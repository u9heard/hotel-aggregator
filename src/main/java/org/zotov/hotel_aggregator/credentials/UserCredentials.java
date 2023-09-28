package org.zotov.hotel_aggregator.credentials;

import java.util.Base64;

public class UserCredentials {
    private final String username;
    private final String password;

    public UserCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserCredentials(String base64Credentials){
        byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
        String decodedCredentials = new String(decodedBytes);

        String[] credentials = decodedCredentials.split(":");
        this.username = credentials[0];
        this.password = credentials[1];
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
