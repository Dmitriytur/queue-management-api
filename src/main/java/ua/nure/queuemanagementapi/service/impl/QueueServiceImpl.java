package ua.nure.queuemanagementapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.queuemanagementapi.entity.QueueEntity;
import ua.nure.queuemanagementapi.entity.TimeSlotEntity;
import ua.nure.queuemanagementapi.repository.QueueRepository;
import ua.nure.queuemanagementapi.repository.TimeSlotRepository;
import ua.nure.queuemanagementapi.service.QueueService;
import ua.nure.queuemanagementapi.service.SmsService;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QueueServiceImpl implements QueueService {

    @Autowired
    private QueueRepository queueRepository;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Autowired
    private SmsService smsService;

    @Autowired
    private DateTimeFormatter dateTimeFormatter;

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

    @Override
    public void sendNotifications(String queueId, String message) {
        QueueEntity queue = queueRepository.getOne(queueId);
        queue.getTimeSlots().stream()
                .filter(ts -> ts.getClient() != null)
                .forEach(ts -> sendNotificationToClient(queue, ts, message));
    }

    private void sendNotificationToClient(QueueEntity queue, TimeSlotEntity timeSlot, String message) {
        smsService.sendMessages(formatMessageForClient(queue, timeSlot, message),
                Collections.singletonList(timeSlot.getClient().getLogin()));
    }

    private String formatMessageForClient(QueueEntity queue, TimeSlotEntity timeSlot, String message) {
        return String.format("%s Queue: \"%s\", time: %s", message, queue.getName(),
                timeSlot.getStartTime().format(dateTimeFormatter));
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
