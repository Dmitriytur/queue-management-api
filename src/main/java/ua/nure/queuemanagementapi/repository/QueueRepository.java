package ua.nure.queuemanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.queuemanagementapi.entity.CategoryEntity;
import ua.nure.queuemanagementapi.entity.QueueEntity;
import ua.nure.queuemanagementapi.entity.UserEntity;

import java.util.List;

public interface QueueRepository extends JpaRepository<QueueEntity, String> {

    List<QueueEntity> findByCategoryOrderByStartTime(CategoryEntity categoryEntity);

    List<QueueEntity> findByCategoryAndManagerOrderByStartTime(CategoryEntity categoryEntity, UserEntity userEntity);
}
