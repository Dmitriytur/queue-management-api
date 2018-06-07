package ua.nure.queuemanagementapi.entity;

import lombok.Data;
import ua.nure.queuemanagementapi.converter.TimestampConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "time_slots")
public class TimeSlotEntity extends AbstractEntity {

    @Column(name = "start_time")
    @Convert(converter = TimestampConverter.class)
    private ZonedDateTime startTime;

    @Column(name = "duration")
    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "queue_id")
    private QueueEntity queue;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private UserEntity client;

    @Column(name = "client_details")
    private String clientDetails;
}
