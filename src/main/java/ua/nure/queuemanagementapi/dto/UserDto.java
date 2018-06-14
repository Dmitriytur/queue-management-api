package ua.nure.queuemanagementapi.dto;

import lombok.Data;

@Data
public class UserDto {

    private String id;

    private String login;

    private boolean activated;

}
