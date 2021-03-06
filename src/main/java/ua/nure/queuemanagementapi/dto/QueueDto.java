package ua.nure.queuemanagementapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class QueueDto {

    private String id;

    private String name;

    private String description;

    private Long startTime;

    private Long endTime;

    private Integer duration;

    private List<TimeSlotDto> timeSlots;
}
