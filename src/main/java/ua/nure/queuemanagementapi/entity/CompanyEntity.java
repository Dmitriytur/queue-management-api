package ua.nure.queuemanagementapi.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "companies")
public class CompanyEntity {

    @Id
    private String id;

    private String name;

    private String address;

    private String description;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private UserEntity manager;

    @ManyToOne
    @JoinColumn(name = "root_category_id")
    private CategoryEntity rootCategory;
}
