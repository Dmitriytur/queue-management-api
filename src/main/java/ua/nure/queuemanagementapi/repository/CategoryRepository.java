package ua.nure.queuemanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.queuemanagementapi.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {

}
