package ua.nure.queuemanagementapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.queuemanagementapi.converter.service.ExtendedConversionService;
import ua.nure.queuemanagementapi.dto.CreateQueueDto;
import ua.nure.queuemanagementapi.dto.QueueDto;
import ua.nure.queuemanagementapi.entity.CategoryEntity;
import ua.nure.queuemanagementapi.entity.QueueEntity;
import ua.nure.queuemanagementapi.entity.UserEntity;
import ua.nure.queuemanagementapi.repository.QueueRepository;
import ua.nure.queuemanagementapi.service.QueueService;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/queues")
public class QueueController {

    @Autowired
    private QueueRepository queueRepository;

    @Autowired
    private QueueService queueService;

    @Autowired
    private ExtendedConversionService conversionService;

    @PostMapping
    public void add(@RequestBody CreateQueueDto dto) {
        QueueEntity entity = conversionService.convert(dto, QueueEntity.class);
        queueService.add(entity);
    }

    @GetMapping
    public List<QueueDto> find(
            @RequestParam("categoryId") String categoryId,
            @RequestParam("startDate") Long startDate,
            @RequestParam("endDate") Long endDate,
            @RequestParam(value = "managerId", required = false) String managerId) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(categoryId);
        ZonedDateTime startDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(startDate), ZoneOffset.UTC);
        ZonedDateTime endDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(endDate), ZoneOffset.UTC);
        List<QueueEntity> queues;
        if (managerId == null) {
            queues = queueRepository.findByCategoryOrderByStartTime(categoryEntity);
        } else {
            UserEntity manager = new UserEntity();
            manager.setId(managerId);
            queues = queueRepository.findByCategoryAndManagerOrderByStartTime(categoryEntity, manager);
        }
        queues = queues.stream()
                .filter(q ->
                        !q.getStartTime().isBefore(startDateTime) &&
                        q.getStartTime().isBefore(endDateTime))
                .collect(Collectors.toList());
        queues.stream()
                .map(QueueEntity::getTimeSlots)
                .forEach(timeSlots -> timeSlots.sort((o1, o2) ->
                        o1.getStartTime().compareTo(o2.getStartTime())));
        return conversionService.convertAll(queues, QueueDto.class);
    }

    @PostMapping("/{queueId}/notifications")
    public void sendNotificationsToQueue(@PathVariable String queueId,  @RequestBody String message) {
        queueService.sendNotifications(queueId, message);
    }

}
