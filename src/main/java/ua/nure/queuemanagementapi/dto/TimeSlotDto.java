package ua.nure.queuemanagementapi.dto;

import lombok.Data;

@Data
public class TimeSlotDto {

    private String id;

    private String startTime;

    private Integer duration;

    private UserDto client;

    private String clientDetails;
}
