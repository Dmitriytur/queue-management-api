package ua.nure.queuemanagementapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.nure.queuemanagementapi.converter.service.ExtendedConversionService;
import ua.nure.queuemanagementapi.dto.TimeSlotDto;
import ua.nure.queuemanagementapi.dto.UserDto;
import ua.nure.queuemanagementapi.entity.TimeSlotEntity;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Component
public class TimeSlotEntityToDtoConverter implements Converter<TimeSlotEntity, TimeSlotDto> {

    private DateTimeFormatter dateTimeFormatter;

    @Autowired
    @Lazy
    private ExtendedConversionService conversionService;

    public TimeSlotEntityToDtoConverter() {
        dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    }

    @Override
    public TimeSlotDto convert(TimeSlotEntity source) {
        TimeSlotDto timeSlotDto = new TimeSlotDto();
        timeSlotDto.setId(source.getId());
        timeSlotDto.setStartTime(source.getStartTime().plus(3, ChronoUnit.HOURS).format(dateTimeFormatter));
        timeSlotDto.setDuration(source.getDuration());
        timeSlotDto.setClientDetails(source.getClientDetails());
        timeSlotDto.setClient(conversionService.convert(source.getClient(), UserDto.class));
        return timeSlotDto;
    }
}
