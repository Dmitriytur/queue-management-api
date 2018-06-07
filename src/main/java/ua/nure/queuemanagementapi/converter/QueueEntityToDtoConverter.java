package ua.nure.queuemanagementapi.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.nure.queuemanagementapi.dto.QueueDto;
import ua.nure.queuemanagementapi.entity.QueueEntity;

import java.time.format.DateTimeFormatter;

@Component
public class QueueEntityToDtoConverter implements Converter<QueueEntity, QueueDto> {

    private DateTimeFormatter dateTimeFormatter;

    public QueueEntityToDtoConverter() {
        dateTimeFormatter = DateTimeFormatter.ISO_TIME;
    }

    @Override
    public QueueDto convert(QueueEntity source) {
        QueueDto queueDto = new QueueDto();
        queueDto.setId(source.getId());
        queueDto.setName(source.getName());
        queueDto.setDescription(source.getDescription());
        queueDto.setStartTime(source.getStartTime().format(dateTimeFormatter));
        queueDto.setEndTime(source.getEndTime().format(dateTimeFormatter));
        return queueDto;
    }
}
