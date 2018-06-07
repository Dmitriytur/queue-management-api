package ua.nure.queuemanagementapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.queuemanagementapi.dto.UpdateTimeSlotDto;
import ua.nure.queuemanagementapi.entity.Role;
import ua.nure.queuemanagementapi.entity.TimeSlotEntity;
import ua.nure.queuemanagementapi.entity.UserEntity;
import ua.nure.queuemanagementapi.repository.TimeSlotRepository;
import ua.nure.queuemanagementapi.repository.UserRepository;

@Service
public class TimeSlotServiceImpl implements TimeSlotService {

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Autowired
    private UserRepository userRepository;

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

        return timeSlotRepository.save(targetSlot);
    }


}
