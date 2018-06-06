package ua.nure.queuemanagementapi.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.nure.queuemanagementapi.dto.UserDto;
import ua.nure.queuemanagementapi.entity.UserEntity;

@Component
public class UserEntityToDtoConverter implements Converter<UserEntity, UserDto> {

    @Override
    public UserDto convert(UserEntity source) {
        UserDto userDto = new UserDto();
        userDto.setId(source.getId());
        userDto.setLogin(source.getLogin());
        return userDto;
    }
}
