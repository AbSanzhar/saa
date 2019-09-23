package kz.saa.prototype.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "dissovet_member")
public class DissovetMemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "member_type")
    private String memberType;

    @Column(name = "academic_degree")
    private String academicDegree;

    @Column(name = "spec_code")
    private String specCode;

    @Column(name = "dis_spec_code")
    private String disSpecCode;

    @Column(name = "work_place")
    private String workPlace;

    @Column(name = "dis_position")
    private String disPosition;

    @Column(name = "user_id")
    private Long userId;
//    @JsonIgnore
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private UserEntity member;

    @Column(name = "dis_id")
    private Long disId;

//    @JsonIgnore
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "dis_id")
//    private DissovetEntity dissovet;
}
