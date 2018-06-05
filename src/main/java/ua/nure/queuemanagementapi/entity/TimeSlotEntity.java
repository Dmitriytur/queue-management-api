package ua.nure.queuemanagementapi.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "time_slots")
public class TimeSlotEntity {

    @Id
    private String id;

    @Column(name = "start_time")
    private ZonedDateTime startTime;

    @Column(name = "duration")
    private Long duration;

    @ManyToOne
    @JoinColumn(name = "queue_id")
    private QueueEntity queue;

    @Column(name = "client_id")
    private UserEntity client;

    @Column(name = "client_details")
    private String clientDetails;
}
