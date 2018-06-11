package ua.nure.queuemanagementapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.queuemanagementapi.dto.UpdateTimeSlotDto;
import ua.nure.queuemanagementapi.entity.Role;
import ua.nure.queuemanagementapi.entity.TimeSlotEntity;
import ua.nure.queuemanagementapi.entity.UserEntity;
import ua.nure.queuemanagementapi.repository.TimeSlotRepository;
import ua.nure.queuemanagementapi.repository.UserRepository;
import ua.nure.queuemanagementapi.service.SmsService;
import ua.nure.queuemanagementapi.service.TimeSlotService;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

@Service
public class TimeSlotServiceImpl implements TimeSlotService {

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SmsService smsService;

    @Autowired
    private DateTimeFormatter dateTimeFormatter;

    @Override
    public TimeSlotEntity assign(String slotId, UpdateTimeSlotDto dto) {
        UserEntity client = userRepository.findByLogin(dto.getPhone());
        if (client == null) {
            client = new UserEntity();
            client.setLogin(dto.getPhone());
            client.setRole(Role.CLIENT);
            userRepository.save(client);
        }
        TimeSlotEntity targetSlot = timeSlotRepository.getOne(slotId);
        targetSlot.setClient(client);
        targetSlot.setClientDetails(dto.getDetails());

        TimeSlotEntity savedSlot = timeSlotRepository.save(targetSlot);
        smsService.sendMessages(formatSmsMessage(savedSlot),
                Collections.singletonList(targetSlot.getClient().getLogin()));

        return savedSlot;
    }

    private String formatSmsMessage(TimeSlotEntity timeSlotEntity) {
        return String.format("You have enrolled to queue \"%s\" on %s", timeSlotEntity.getQueue().getName(),
                timeSlotEntity.getStartTime().plus(3, ChronoUnit.HOURS).format(dateTimeFormatter));
    }


}
