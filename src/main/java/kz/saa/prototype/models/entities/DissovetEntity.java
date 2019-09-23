package kz.saa.prototype.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "dissovet")
public class DissovetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dis_id")
    private Long disId;

    @Column(name = "dis_info")
    private String disInfo;

    @Column(name = "dis_start_date")
    private Date disStartDate;

    @Column(name = "dis_stop_date")
    private Date disStopDate;

    @Column(name = "ministry_order")
    private String ministryOrder;

    @Column(name = "members_num")
    private Integer membersNum;

    @Column(name = "secretary_id")
    private Long secretaryId;
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "secretary_id")
//    private UserEntity secretary;



//    @OneToMany(mappedBy = "dissovet", cascade = CascadeType.ALL)
//    private List<DissovetMemberEntity> dissovetMembers;


}
