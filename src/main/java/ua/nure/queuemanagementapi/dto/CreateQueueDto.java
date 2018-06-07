package ua.nure.queuemanagementapi.dto;

import lombok.Data;

@Data
public class CreateQueueDto {

    private String name;

    private String description;

    private Long startTime;

    private Long endTime;

    private Integer duration;

    private String managerId;

    private String categoryId;

}
