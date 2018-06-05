package ua.nure.queuemanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.queuemanagementapi.entity.TimeSlotEntity;

public interface TimeSlotRepository extends JpaRepository<TimeSlotEntity, String> {
}
