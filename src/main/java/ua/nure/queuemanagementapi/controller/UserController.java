package ua.nure.queuemanagementapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.queuemanagementapi.converter.service.ExtendedConversionService;
import ua.nure.queuemanagementapi.dto.UserDto;
import ua.nure.queuemanagementapi.entity.CompanyEntity;
import ua.nure.queuemanagementapi.entity.Role;
import ua.nure.queuemanagementapi.entity.UserEntity;
import ua.nure.queuemanagementapi.repository.CompanyRepository;
import ua.nure.queuemanagementapi.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ExtendedConversionService conversionService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping("/sign-up")
    public void signUp(@RequestBody UserEntity user) {
        if (user.getRole() == Role.MANAGER) {
            user.setCompany(companyRepository.getOne(user.getCompany().getId()));
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @GetMapping("/{userId}/company")
    public CompanyEntity getByAdminId(@PathVariable String userId) {
        return userRepository.findById(userId).map(UserEntity::getCompany).orElse(null);
    }

    @GetMapping("/{userId}/company/managers")
    public List<UserDto> getManagersByAdmin(@PathVariable String userId) {
        UserEntity admin = userRepository.getOne(userId);
        List<UserEntity> users = userRepository.findByCompanyAndRoleNot(admin.getCompany(), Role.ADMIN);
        return conversionService.convertAll(users, UserDto.class);
    }
}