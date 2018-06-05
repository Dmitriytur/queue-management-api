package ua.nure.queuemanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.queuemanagementapi.entity.CompanyEntity;

public interface CategoryRepository extends JpaRepository<CompanyEntity, String> {

}
