package ua.nure.queuemanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.queuemanagementapi.entity.CompanyEntity;
import ua.nure.queuemanagementapi.entity.Role;
import ua.nure.queuemanagementapi.entity.UserEntity;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByLogin(String login);

    List<UserEntity> findByCompanyAndRoleNot(CompanyEntity companyEntity, Role roleNot);
}