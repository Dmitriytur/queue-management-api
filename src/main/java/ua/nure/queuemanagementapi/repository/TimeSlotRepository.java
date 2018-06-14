package ua.nure.queuemanagementapi.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.queuemanagementapi.entity.QueueEntity;
import ua.nure.queuemanagementapi.entity.TimeSlotEntity;
import ua.nure.queuemanagementapi.entity.UserEntity;

public interface TimeSlotRepository extends JpaRepository<TimeSlotEntity, String> {
    boolean existsByQueueAndClient(QueueEntity queue, UserEntity client);
}
