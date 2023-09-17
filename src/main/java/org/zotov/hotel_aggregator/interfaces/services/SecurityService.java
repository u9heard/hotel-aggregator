package org.zotov.hotel_aggregator.interfaces.services;

import org.zotov.hotel_aggregator.credentials.UserCredentials;

public interface SecurityService<T> {
    T authorize(UserCredentials credentials, String role);
}
