package dev.titans.services;

import dev.titans.dtos.LoginCredentials;

public interface LoginService {
    String authenticateUser(LoginCredentials loginCredentials);
}
