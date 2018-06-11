package ua.nure.queuemanagementapi.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.nure.queuemanagementapi.service.SmsService;
import ua.nure.queuemanagementapi.util.SMSSender;

import java.util.List;

@Service
public class SmsServiceImpl implements SmsService {

    private SMSSender smsSender;

    public SmsServiceImpl(@Value("${sms.service.login}") String smsServiceLogin,
                          @Value("${sms.service.password}") String smsServicePassword) {
        smsSender = new SMSSender(smsServiceLogin, smsServicePassword);
    }

    @Override
    public void sendMessages(String message, List<String> numbers) {
        String joinedNumbers = String.join(";", numbers.toArray(new String[0]));
        String[] strings = smsSender.sendSms(joinedNumbers, message, 1, "", "", 0, "", "");
        if (strings.length != 4) {
            throw new IllegalStateException("Error appeared while sending sms");
        }
        System.out.println((String.format("Sending message: %s%nTo numbers: %s%n", message, joinedNumbers)));
    }
}
