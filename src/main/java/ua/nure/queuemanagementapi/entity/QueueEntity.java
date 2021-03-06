package ua.nure.queuemanagementapi.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ua.nure.queuemanagementapi.converter.TimestampConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "timeSlots")
@Entity
@Table(name = "queues")
public class QueueEntity extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_time")
    @Convert(converter = TimestampConverter.class)
    private ZonedDateTime startTime;

    @Column(name = "end_time")
    @Convert(converter = TimestampConverter.class)
    private ZonedDateTime endTime;

    @Column(name = "slot_duration")
    private Integer slotDuration;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private UserEntity manager;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @OneToMany(mappedBy = "queue")
    private List<TimeSlotEntity> timeSlots;

}
