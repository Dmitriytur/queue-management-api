package ua.nure.queuemanagementapi.service;

import ua.nure.queuemanagementapi.entity.QueueEntity;

public interface QueueService {

    QueueEntity add(QueueEntity queue);

    void sendNotifications(String queueId, String message);
}
