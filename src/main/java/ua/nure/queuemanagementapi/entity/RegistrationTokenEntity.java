package ua.nure.queuemanagementapi.entity;

import lombok.Data;
import ua.nure.queuemanagementapi.converter.TimestampConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "registration_tokens")
public class RegistrationTokenEntity  {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


    @Column(name = "expiration_date")
    @Convert(converter = TimestampConverter.class)
    private ZonedDateTime expirationDate;
}
