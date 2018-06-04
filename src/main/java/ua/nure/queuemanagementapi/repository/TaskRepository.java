package ua.nure.queuemanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.queuemanagementapi.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
