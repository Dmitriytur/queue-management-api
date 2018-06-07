package ua.nure.queuemanagementapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.queuemanagementapi.dto.UpdateTimeSlotDto;
import ua.nure.queuemanagementapi.service.TimeSlotService;

@RestController
@RequestMapping("/time-slots")
public class TimeSlotsController {

    @Autowired
    private TimeSlotService timeSlotService;

    @PutMapping("/{slotId}")
    private void add(@PathVariable String slotId, @RequestBody UpdateTimeSlotDto dto) {
        timeSlotService.assign(slotId, dto);
    }
}
