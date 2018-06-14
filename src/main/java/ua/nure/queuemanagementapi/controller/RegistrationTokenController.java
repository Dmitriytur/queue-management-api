package ua.nure.queuemanagementapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.queuemanagementapi.converter.service.ExtendedConversionService;
import ua.nure.queuemanagementapi.dto.UserDto;
import ua.nure.queuemanagementapi.entity.RegistrationTokenEntity;
import ua.nure.queuemanagementapi.entity.UserEntity;
import ua.nure.queuemanagementapi.repository.RegistrationTokenRepository;
import ua.nure.queuemanagementapi.repository.UserRepository;

@RestController
@RequestMapping("/register-tokens")
public class RegistrationTokenController {

    @Autowired
    private RegistrationTokenRepository registrationTokenRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExtendedConversionService conversionService;



    @PutMapping("/{tokenId}")
    public void registerByToken(@PathVariable String tokenId, @RequestBody String password) {
        RegistrationTokenEntity token = registrationTokenRepository.getOne(tokenId);

        UserEntity user = token.getUser();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @GetMapping("/{tokenId}/user")
    public UserDto getUserByToken(@PathVariable String tokenId) {
        return conversionService.convert(registrationTokenRepository.getOne(tokenId).getUser(), UserDto.class);
    }

}
