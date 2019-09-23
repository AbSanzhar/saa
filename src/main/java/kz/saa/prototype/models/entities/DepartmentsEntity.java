package kz.saa.prototype.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "departments")
public class DepartmentsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id")
    private Long deptId;

    @Column(name = "dept_name")
    private String deptName;

    @Column(name = "announcement")
    private String announcement;

    @Column(name = "head_of_dept")
    private Long headOfDept;

    @JsonIgnore
    //@JsonManagedReference(value = "dept")
    //@JsonIgnore
    @OneToMany(mappedBy = "primaryKey.departmentsEntity", cascade = CascadeType.ALL)
    private Set<UsersDeptsEntity> usersDeptsEntities;

}
