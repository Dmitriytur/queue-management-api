package ua.nure.queuemanagementapi.dto;

import lombok.Data;

@Data
public class QueueDto {

    private String id;

    private String name;

    private String description;

    private String startTime;

    private String endTime;

    private Integer duration;
}
