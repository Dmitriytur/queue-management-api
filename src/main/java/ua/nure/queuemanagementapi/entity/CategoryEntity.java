package ua.nure.queuemanagementapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@ToString(exclude = "options")
@Entity
@Table(name = "categories")
public class CategoryEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    @JsonIgnore
    private CategoryEntity parent;

    @Column(name = "value")
    private String value;

    @Column(name = "next_category_name")
    private String nextCategoryName;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<CategoryEntity> options;
}
