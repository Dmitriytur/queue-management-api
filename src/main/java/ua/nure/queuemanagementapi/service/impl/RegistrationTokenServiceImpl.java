package ua.nure.queuemanagementapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.queuemanagementapi.entity.RegistrationTokenEntity;
import ua.nure.queuemanagementapi.entity.UserEntity;
import ua.nure.queuemanagementapi.repository.RegistrationTokenRepository;
import ua.nure.queuemanagementapi.service.RegistrationTokenService;

import java.security.SecureRandom;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

@Service
public class RegistrationTokenServiceImpl implements RegistrationTokenService {


    private long tokenLiveTime = 24;

    @Autowired
    private RegistrationTokenRepository registrationTokenRepository;


    @Override
    public RegistrationTokenEntity addTokenForUser(UserEntity user) {
        RegistrationTokenEntity tokenEntity = new RegistrationTokenEntity();

        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[128];
        random.nextBytes(bytes);
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        String token = encoder.encodeToString(bytes);

        tokenEntity.setId(token);
        tokenEntity.setUser(user);
        tokenEntity.setExpirationDate(ZonedDateTime.now(ZoneOffset.UTC).plus(tokenLiveTime, ChronoUnit.HOURS));
        registrationTokenRepository.save(tokenEntity);

        return tokenEntity;
    }
}
