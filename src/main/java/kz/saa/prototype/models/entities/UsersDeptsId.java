package kz.saa.prototype.models.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;


@Embeddable
public class UsersDeptsId implements Serializable {
//    @Column(name = "user_id")
//    private Long userId;
//
//    @Column(name = "dept_id")
//    private Long deptId;
    private UserEntity userEntity;
    private DepartmentsEntity departmentsEntity;

    //@JsonBackReference("user")
   // @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    public UserEntity getUserEntity() {
        return userEntity;
    }
    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    //@JsonBackReference("dept")
   // @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    public DepartmentsEntity getDepartmentsEntity() {
        return departmentsEntity;
    }
    public void setDepartmentsEntity(DepartmentsEntity departmentsEntity) {
        this.departmentsEntity = departmentsEntity;
    }
}
