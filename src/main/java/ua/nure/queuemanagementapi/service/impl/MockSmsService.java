package ua.nure.queuemanagementapi.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ua.nure.queuemanagementapi.service.SmsService;

import java.util.List;

@Service
@Primary
public class MockSmsService implements SmsService {

    @Override
    public void sendMessages(String message, List<String> numbers) {
        String joinedNumbers = String.join(", ", numbers.toArray(new String[0]));
        System.out.println((String.format("Sending message: %s%nTo numbers: %s%n", message, joinedNumbers)));
    }
}
