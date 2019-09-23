package kz.saa.prototype.services;

import kz.saa.prototype.models.pojos.Dissovet;
import kz.saa.prototype.models.pojos.DissovetMember;
import kz.saa.prototype.models.pojos.impl.DefaultDissovet;
import kz.saa.prototype.models.pojos.json.DissovetJson;
import kz.saa.prototype.models.pojos.json.DissovetMemberJson;

import java.util.List;

public interface DissovetService {
    List<Dissovet> showAllDissovets();

    List<DissovetMember> showByUser(Long userId);

    List<Dissovet> showBySecretary(Long secretaryId);

    DefaultDissovet showDissovet(Long disId) throws Exception;

    Long addDissovet(DissovetJson dissovetJson);

    String addDissovetUserMember(Long disId, DissovetMemberJson dissovetMemberJson);

    String addDissovetMember(Long disId, DissovetMemberJson dissovetMemberJson);

    String updateDissovet(Long disId, DissovetJson dissovetJson);

    String updateDissovetMember(Long disId, DissovetMemberJson dissovetMemberJson);

    String deleteDissovet(Long disId);

    String deleteDisMember(Long memberId);
}
