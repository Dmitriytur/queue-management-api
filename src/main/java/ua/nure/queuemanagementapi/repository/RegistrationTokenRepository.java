package ua.nure.queuemanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.queuemanagementapi.entity.RegistrationTokenEntity;

@Repository
public interface RegistrationTokenRepository extends JpaRepository<RegistrationTokenEntity, String> {
}
