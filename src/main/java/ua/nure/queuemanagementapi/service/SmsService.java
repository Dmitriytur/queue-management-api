package ua.nure.queuemanagementapi.service;

import java.util.List;

public interface SmsService {

    void sendMessages(String message, List<String> numbers);
}
