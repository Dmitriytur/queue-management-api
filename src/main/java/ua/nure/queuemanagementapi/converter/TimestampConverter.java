package ua.nure.queuemanagementapi.converter;

import javax.persistence.AttributeConverter;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class TimestampConverter implements AttributeConverter<ZonedDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(ZonedDateTime zonedTime) {
        if (zonedTime == null) {
            return null;
        }
        return new Timestamp(zonedTime.toInstant().toEpochMilli());
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(Timestamp localTime) {
        if (localTime == null) {
            return null;
        }
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(localTime.getTime()), ZoneOffset.UTC);
    }

}