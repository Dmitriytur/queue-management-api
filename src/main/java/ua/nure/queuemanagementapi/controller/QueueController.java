package ua.nure.queuemanagementapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.queuemanagementapi.converter.service.ExtendedConversionService;
import ua.nure.queuemanagementapi.dto.QueueDto;
import ua.nure.queuemanagementapi.entity.CategoryEntity;
import ua.nure.queuemanagementapi.entity.QueueEntity;
import ua.nure.queuemanagementapi.repository.QueueRepository;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/queue")
public class QueueController {

    @Autowired
    private QueueRepository queueRepository;

    @Autowired
    private ExtendedConversionService conversionService;

    @GetMapping
    public List<QueueDto> find(
            @RequestParam("categoryId") String categoryId,
            @RequestParam("startDate") Long startDate,
            @RequestParam("endDate") Long endDate) {
//        QueueEntity queueEntity = new QueueEntity();
//        queueEntity.setName("Queue name");
//        queueEntity.setDescription("Test queue");
//        queueEntity.setStartTime(ZonedDateTime.of(2018, 6, 7,
//                8, 0, 0, 0, ZoneOffset.UTC));
//        queueEntity.setEndTime(ZonedDateTime.of(2018, 6, 7,
//                19, 0, 0, 0, ZoneOffset.UTC));
//
//        UserEntity manager = new UserEntity();
//        manager.setId("686b4b4c-f52a-4b72-b592-9fe1753c6091");
//        queueEntity.setManager(manager);
//
//        CategoryEntity category = new CategoryEntity();
//        category.setId("8e95e800-c4ed-47e1-a5a0-09b666881c63");
//        queueEntity.setCategory(category);
//
//        queueEntity.setSlotDuration(30);
//
//        queueRepository.save(queueEntity);

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(categoryId);
        ZonedDateTime startDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(startDate), ZoneOffset.UTC);
        ZonedDateTime endDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(endDate), ZoneOffset.UTC);
        List<QueueEntity> byCategory = queueRepository.findByCategory(categoryEntity);
        byCategory = byCategory.stream()
                .filter(q -> q.getStartTime().isAfter(startDateTime) && q.getStartTime().isBefore(endDateTime))
                .collect(Collectors.toList());
        return conversionService.convertAll(byCategory, QueueDto.class);
    }

}
