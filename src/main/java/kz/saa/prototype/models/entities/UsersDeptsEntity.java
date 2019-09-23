package kz.saa.prototype.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users_depts")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.userEntity", joinColumns = @JoinColumn(name = "user_id")),
        @AssociationOverride(name = "primaryKey.departmentsEntity", joinColumns = @JoinColumn(name = "dept_id"))
})
public class UsersDeptsEntity {
    private UsersDeptsId primaryKey = new UsersDeptsId();

    private String userType;


 //   @JsonIgnore
    @EmbeddedId
    public UsersDeptsId getPrimaryKey() {
        return primaryKey;
    }
    public void setPrimaryKey(UsersDeptsId primaryKey) {
        this.primaryKey = primaryKey;
    }


    @JsonIgnore
    @Transient
    public UserEntity getUserEntity() {
        return primaryKey.getUserEntity();
    }
    public void setUserEntity(UserEntity userEntity) {
        getPrimaryKey().setUserEntity(userEntity);
    }


    @JsonIgnore
    @Transient
    public DepartmentsEntity getDepartmentsEntity() {
        return primaryKey.getDepartmentsEntity();
    }
    public void setDepartmentsEntity(DepartmentsEntity departmentsEntity) {
        getPrimaryKey().setDepartmentsEntity(departmentsEntity);
    }

    @Column(name = "user_type")
    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }

//    @EmbeddedId
//    private UsersDeptsId primaryKey;
//
//    @JsonBackReference(value = "user")
//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("userId")
//    @JoinColumn(name = "user_id")
//    private UserEntity userEntity;
//
//    @JsonBackReference(value = "dept")
//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("deptId")
//    @JoinColumn(name = "dept_id")
//    private DepartmentsEntity departmentsEntity;
//
//    @Column(name = "user_type")
//    private String userType;

}
