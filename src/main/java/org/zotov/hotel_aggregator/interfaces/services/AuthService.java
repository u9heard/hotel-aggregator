package org.zotov.hotel_aggregator.interfaces.services;

import org.zotov.hotel_aggregator.credentials.UserCredentials;

public interface AuthService {
    Long authorize(UserCredentials credentials, String role);
}
