package kz.saa.prototype.models.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "email")
    private String email;

    @Column(name = "description")
    private String description;

    @Column(name = "own")
    private Boolean own;


    @JsonIgnore
    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private LoginEntity loginEntity;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")})
    private List<RolesEntity> roles;

    @JsonIgnore
    //@JsonManagedReference(value = "user")
    //@JsonIgnore
    @OneToMany(mappedBy = "primaryKey.userEntity",cascade = CascadeType.ALL)
    private Set<UsersDeptsEntity> usersDeptsEntities;

//    @OneToOne(mappedBy = "secretary", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
//    private DissovetEntity dissovetEntity;

//    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
//    private DissovetMemberEntity dissovetMemberEntity;

}
