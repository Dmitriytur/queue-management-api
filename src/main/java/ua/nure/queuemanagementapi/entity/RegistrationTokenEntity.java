package ua.nure.queuemanagementapi.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ua.nure.queuemanagementapi.converter.TimestampConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "registration_tokens")
public class RegistrationTokenEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


    @Column(name = "expiration_date")
    @Convert(converter = TimestampConverter.class)
    private ZonedDateTime expirationDate;
}
