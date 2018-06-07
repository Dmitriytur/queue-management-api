package ua.nure.queuemanagementapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.queuemanagementapi.entity.QueueEntity;
import ua.nure.queuemanagementapi.entity.TimeSlotEntity;
import ua.nure.queuemanagementapi.repository.QueueRepository;
import ua.nure.queuemanagementapi.repository.TimeSlotRepository;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QueueServiceImpl implements QueueService {

    @Autowired
    private QueueRepository queueRepository;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Override
    @Transactional
    public QueueEntity add(QueueEntity queue) {
        List<TimeSlotEntity> slots = splitIntoTimeSlots(queue.getStartTime(),
                queue.getEndTime(), queue.getSlotDuration())
                .stream()
                .map(time -> initTimeSlot(queue, time))
                .collect(Collectors.toList());
        QueueEntity savedQueue = queueRepository.save(queue);
        timeSlotRepository.saveAll(slots);
        return savedQueue;
    }


    private List<ZonedDateTime> splitIntoTimeSlots(ZonedDateTime start, ZonedDateTime end, int duration) {
        List<ZonedDateTime> slots = new ArrayList<>();
        ZonedDateTime slotStart = start;

        while (!slotStart.plus(duration, ChronoUnit.MINUTES).isAfter(end)) {
            slots.add(slotStart);
            slotStart = slotStart.plus(duration, ChronoUnit.MINUTES);
        }
        return slots;
    }

    private TimeSlotEntity initTimeSlot(QueueEntity queueEntity, ZonedDateTime start) {
        TimeSlotEntity slot = new TimeSlotEntity();
        slot.setStartTime(start);
        slot.setQueue(queueEntity);
        slot.setDuration(queueEntity.getSlotDuration());
        return slot;

    }
}
