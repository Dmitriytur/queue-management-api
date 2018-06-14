package ua.nure.queuemanagementapi.service;

import ua.nure.queuemanagementapi.entity.RegistrationTokenEntity;
import ua.nure.queuemanagementapi.entity.UserEntity;

public interface RegistrationTokenService {

    RegistrationTokenEntity addTokenForUser(UserEntity user);
}
