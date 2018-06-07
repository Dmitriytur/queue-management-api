package ua.nure.queuemanagementapi.service;

import ua.nure.queuemanagementapi.dto.UpdateTimeSlotDto;
import ua.nure.queuemanagementapi.entity.TimeSlotEntity;

public interface TimeSlotService {

    TimeSlotEntity assign(String slotId, UpdateTimeSlotDto dto);
}
