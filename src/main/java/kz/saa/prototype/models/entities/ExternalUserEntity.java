package kz.saa.prototype.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "external_users")
public class ExternalUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ex_id")
    private Long exId;

    @Column(name = "iin")
    private String iin;

    @Column(name = "place_of_work")
    private String placeOfWork;

    @Column(name = "user_id")
    private Long userId;
}
