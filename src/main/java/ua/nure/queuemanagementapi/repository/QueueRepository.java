package ua.nure.queuemanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.queuemanagementapi.entity.QueueEntity;

public interface QueueRepository extends JpaRepository<QueueEntity, String> {
}
