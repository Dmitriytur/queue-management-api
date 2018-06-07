package ua.nure.queuemanagementapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.nure.queuemanagementapi.converter.service.ExtendedConversionService;
import ua.nure.queuemanagementapi.dto.QueueDto;
import ua.nure.queuemanagementapi.dto.TimeSlotDto;
import ua.nure.queuemanagementapi.entity.QueueEntity;

@Component
public class QueueEntityToDtoConverter implements Converter<QueueEntity, QueueDto> {

    @Lazy
    @Autowired
    private ExtendedConversionService conversionService;

    @Override
    public QueueDto convert(QueueEntity source) {
        QueueDto queueDto = new QueueDto();
        queueDto.setId(source.getId());
        queueDto.setName(source.getName());
        queueDto.setDescription(source.getDescription());
        queueDto.setStartTime(source.getStartTime().toInstant().toEpochMilli());
        queueDto.setEndTime(source.getEndTime().toInstant().toEpochMilli());
        queueDto.setDuration(source.getSlotDuration());
        queueDto.setTimeSlots(conversionService.convertAll(source.getTimeSlots(), TimeSlotDto.class));
        return queueDto;
    }
}
