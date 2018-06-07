package ua.nure.queuemanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.queuemanagementapi.entity.CategoryEntity;
import ua.nure.queuemanagementapi.entity.QueueEntity;

import java.util.List;

public interface QueueRepository extends JpaRepository<QueueEntity, String> {

    List<QueueEntity> findByCategory(CategoryEntity categoryEntity);
}
