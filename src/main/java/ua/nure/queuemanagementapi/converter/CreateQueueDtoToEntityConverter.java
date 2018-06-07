package ua.nure.queuemanagementapi.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.nure.queuemanagementapi.dto.CreateQueueDto;
import ua.nure.queuemanagementapi.entity.CategoryEntity;
import ua.nure.queuemanagementapi.entity.QueueEntity;
import ua.nure.queuemanagementapi.entity.UserEntity;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Component
public class CreateQueueDtoToEntityConverter implements Converter<CreateQueueDto, QueueEntity> {

    @Override
    public QueueEntity convert(CreateQueueDto source) {
        QueueEntity queueEntity = new QueueEntity();
        queueEntity.setName(source.getName());
        queueEntity.setDescription(source.getDescription());
        queueEntity.setStartTime(ZonedDateTime.ofInstant(Instant.ofEpochSecond(source.getStartTime()), ZoneOffset.UTC));
        queueEntity.setEndTime(ZonedDateTime.ofInstant(Instant.ofEpochSecond(source.getEndTime()), ZoneOffset.UTC));
        queueEntity.setSlotDuration(source.getDuration());

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(source.getCategoryId());
        queueEntity.setCategory(categoryEntity);

        UserEntity manager = new UserEntity();
        manager.setId(source.getManagerId());
        queueEntity.setManager(manager);

        return queueEntity;
    }
}
