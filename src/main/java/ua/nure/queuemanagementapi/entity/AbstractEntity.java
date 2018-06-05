package ua.nure.queuemanagementapi.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.util.UUID;

@Data
@MappedSuperclass
public class AbstractEntity {

    @Id
    private String id;

    @PrePersist
    public void setUuid() {
        this.setId(UUID.randomUUID().toString());
    }
}
