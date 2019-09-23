package kz.saa.prototype.services.impl;

import kz.saa.prototype.models.entities.DissovetEntity;
import kz.saa.prototype.models.entities.DissovetMemberEntity;
import kz.saa.prototype.models.entities.RolesEntity;
import kz.saa.prototype.models.entities.UserEntity;
import kz.saa.prototype.models.pojos.Dissovet;
import kz.saa.prototype.models.pojos.DissovetMember;
import kz.saa.prototype.models.pojos.impl.DefaultDissovet;
import kz.saa.prototype.models.pojos.impl.DefaultDissovetMember;
import kz.saa.prototype.models.pojos.json.DissovetJson;
import kz.saa.prototype.models.pojos.json.DissovetMemberJson;
import kz.saa.prototype.repositories.DissovetMemberRepository;
import kz.saa.prototype.repositories.DissovetRepository;
import kz.saa.prototype.repositories.RolesRepository;
import kz.saa.prototype.repositories.UserRepository;
import kz.saa.prototype.services.DissovetService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.spi.InFlightMetadataCollector;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class DefaultDissovetService implements DissovetService {

    @Autowired
    private DissovetRepository dissovetRepository;

    @Autowired
    private DissovetMemberRepository dissovetMemberRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public List<Dissovet> showAllDissovets() {
        List<DissovetEntity> dissovetEntities = this.dissovetRepository.findAll();
        return dissovetEntities.stream().map(dissovetEntity ->
                getDissovet(dissovetEntity)).collect(Collectors.toList());
    }

    @Override
    public List<DissovetMember> showByUser(Long userId) {
        List<DissovetMemberEntity> dissovetMemberEntities = this.dissovetMemberRepository.findAllByUserId(userId);
        return dissovetMemberEntities.stream().map(dissovetMemberEntity ->
                getDisUser(dissovetMemberEntity)).collect(Collectors.toList());
    }

    @Override
    public List<Dissovet> showBySecretary(Long secretaryId) {
        List<DissovetEntity> dissovetEntities = this.dissovetRepository.findAllBySecretaryId(secretaryId);
        return dissovetEntities.stream().map(dissovetEntity ->
                getDissovet(dissovetEntity)).collect(Collectors.toList());
    }

    @Override
    public DefaultDissovet showDissovet(Long disId) throws Exception {
        DissovetEntity dissovetEntity = this.dissovetRepository.findById(disId).orElse(null);
        if (dissovetEntity != null) {
            return getDissovet(dissovetEntity);
        } throw new Exception("Dissovet not found");

    }

    @Override
    public Long addDissovet(DissovetJson dissovetJson) {
        DissovetEntity dissovetEntity = new DissovetEntity();

        dissovetEntity.setDisInfo(dissovetJson.getDisInfo());
        dissovetEntity.setDisStartDate(dissovetJson.getDisStartDate());
        dissovetEntity.setDisStopDate(dissovetJson.getDisStopDate());
        dissovetEntity.setMinistryOrder(dissovetJson.getMinistryOrder());
        dissovetEntity.setMembersNum(dissovetJson.getMembersNum());
        dissovetEntity.setSecretaryId(dissovetJson.getSecretaryId());

        this.dissovetRepository.save(dissovetEntity);

        log.info("Dissovet added {}", new Date());
        return dissovetEntity.getDisId();
    }

    @Override
    public String addDissovetUserMember(Long disId, DissovetMemberJson dissovetMemberJson) {
        DissovetEntity dissovetEntity = this.dissovetRepository.findById(disId).orElse(null);
        UserEntity userEntity = this.userRepository.findById(dissovetMemberJson.getUserId()).orElse(null);
        List<RolesEntity> rolez = userEntity.getRoles();
        RolesEntity role = this.rolesRepository.selectByDis();
        List<RolesEntity> roles = new ArrayList<>(rolez);
        roles.add(role);
        if (dissovetEntity != null) {
            DissovetMemberEntity dissovetMemberEntity = new DissovetMemberEntity();

            dissovetMemberEntity.setFirstName(userEntity.getFirstName());
            dissovetMemberEntity.setLastName(userEntity.getLastName());
            dissovetMemberEntity.setMemberType(dissovetMemberJson.getMemberType());
            dissovetMemberEntity.setAcademicDegree(dissovetMemberJson.getAcademicDegree());
            dissovetMemberEntity.setSpecCode(dissovetMemberJson.getSpecCode());
            dissovetMemberEntity.setDisSpecCode(dissovetMemberJson.getDisSpecCode());
            dissovetMemberEntity.setWorkPlace(dissovetMemberJson.getWorkPlace());
            dissovetMemberEntity.setDisPosition(dissovetMemberJson.getDisPosition());
            dissovetMemberEntity.setUserId(dissovetMemberJson.getUserId());
            dissovetMemberEntity.setDisId(disId);
            this.dissovetMemberRepository.save(dissovetMemberEntity);
            if (userEntity != null) {
                //userEntity.setRoles(rolesEntities);
                userEntity.setRoles(roles);
                this.userRepository.save(userEntity);
            }

            log.info("Added members for dissovet ID: {} {}", disId, new Date());
            return "Added members";
        }
        else {
            return "dissoveta net";
        }
    }

    @Override
    public String addDissovetMember(Long disId, DissovetMemberJson dissovetMemberJson) {
        DissovetEntity dissovetEntity = this.dissovetRepository.findById(disId).orElse(null);
        if (dissovetEntity != null) {
            DissovetMemberEntity dissovetMemberEntity = new DissovetMemberEntity();

            dissovetMemberEntity.setFirstName(dissovetMemberJson.getFirstName());
            dissovetMemberEntity.setLastName(dissovetMemberJson.getLastName());
            dissovetMemberEntity.setMemberType(dissovetMemberJson.getMemberType());
            dissovetMemberEntity.setAcademicDegree(dissovetMemberJson.getAcademicDegree());
            dissovetMemberEntity.setSpecCode(dissovetMemberJson.getSpecCode());
            dissovetMemberEntity.setDisSpecCode(dissovetMemberJson.getDisSpecCode());
            dissovetMemberEntity.setWorkPlace(dissovetMemberJson.getWorkPlace());
            dissovetMemberEntity.setDisPosition(dissovetMemberJson.getDisPosition());
            dissovetMemberEntity.setDisId(disId);
            this.dissovetMemberRepository.save(dissovetMemberEntity);

            log.info("Added members for dissovet ID: {} {}", disId, new Date());
            return "Added members";
        }
        else {
            return "dissoveta net";
        }
    }


    @Override
    public String updateDissovetMember(Long disId, DissovetMemberJson dissovetMemberJson) {
        DissovetMemberEntity dissovetMemberEntity = this.dissovetMemberRepository.findById(disId).orElse(null);
        if (Objects.nonNull(dissovetMemberEntity)) {
            if (dissovetMemberJson.getFirstName() != null)
                dissovetMemberEntity.setFirstName(dissovetMemberJson.getFirstName());
            if (dissovetMemberJson.getLastName() != null)
                dissovetMemberEntity.setLastName(dissovetMemberJson.getLastName());
            if (dissovetMemberJson.getMemberType() != null)
                dissovetMemberEntity.setMemberType(dissovetMemberJson.getMemberType());
            if (dissovetMemberJson.getAcademicDegree() != null)
                dissovetMemberEntity.setAcademicDegree(dissovetMemberJson.getAcademicDegree());
            if (dissovetMemberJson.getSpecCode() != null)
                dissovetMemberEntity.setSpecCode(dissovetMemberJson.getSpecCode());
            if (dissovetMemberJson.getDisSpecCode() != null)
                dissovetMemberEntity.setDisSpecCode(dissovetMemberJson.getDisSpecCode());
            if (dissovetMemberJson.getWorkPlace() != null)
                dissovetMemberEntity.setWorkPlace(dissovetMemberJson.getWorkPlace());
            if (dissovetMemberJson.getDisPosition() != null)
                dissovetMemberEntity.setDisPosition(dissovetMemberJson.getDisPosition());
            if (dissovetMemberJson.getUserId() != null)
                dissovetMemberEntity.setUserId(dissovetMemberJson.getUserId());
            if (dissovetMemberJson.getDisId() != null)
                dissovetMemberEntity.setDisId(dissovetMemberJson.getDisId());
            this.dissovetMemberRepository.save(dissovetMemberEntity);
            log.info("Dissovet with ID: {} Updated {}", dissovetMemberJson.getMemberId(), new Date());
            return "Updated";
        }
        else {
            return "Dissovet isn't fond";
        }
    }

    @Override
    public String updateDissovet(Long disId, DissovetJson dissovetJson) {
        DissovetEntity dissovetEntity = this.dissovetRepository.findById(disId).orElse(null);
        if (Objects.nonNull(dissovetEntity)) {
            if (dissovetJson.getDisInfo() != null)
                dissovetEntity.setDisInfo(dissovetJson.getDisInfo());
            if (dissovetJson.getDisStartDate() != null)
                dissovetEntity.setDisStartDate(dissovetJson.getDisStartDate());
            if (dissovetJson.getDisStopDate() != null)
                dissovetEntity.setDisStopDate(dissovetJson.getDisStopDate());
            if (dissovetJson.getMinistryOrder() != null)
                dissovetEntity.setMinistryOrder(dissovetJson.getMinistryOrder());
            if (dissovetJson.getMembersNum() != null)
                dissovetEntity.setMembersNum(dissovetJson.getMembersNum());
            if (dissovetJson.getSecretaryId() != null)
                dissovetEntity.setSecretaryId(dissovetJson.getSecretaryId());
            this.dissovetRepository.save(dissovetEntity);
            log.info("Dissovet with ID {} updated on {}", disId, new Date());
            return "Dissovet updated!";
        }
        else {
            return "Dissovet isn't exists";
        }
    }

    @Override
    public String deleteDissovet(Long disId) {
        DissovetEntity dissovetEntity = this.dissovetRepository.findById(disId).orElse(null);
        if (Objects.nonNull(dissovetEntity)) {
            List<DissovetMemberEntity> dissovetMemberEntities = this.dissovetMemberRepository.findByDisId(disId);
            this.dissovetMemberRepository.deleteAll(dissovetMemberEntities);
            this.dissovetRepository.deleteById(disId);
            log.info("Deleted dissovet: {} . {}", dissovetEntity.getDisInfo(), new Date());
            return "Deleted";
        }
        else {
            return "No dissovet with ID";
        }
    }

    @Override
    public String deleteDisMember(Long memberId) {
        DissovetMemberEntity dissovetMemberEntity = this.dissovetMemberRepository.findById(memberId).orElse(null);
        this.dissovetMemberRepository.delete(dissovetMemberEntity);
        return "Deleted";
    }

    //Вспомогательные методы по сути ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::://

    private DefaultDissovetMember getDisUser(DissovetMemberEntity dissovetMemberEntity) {
        DissovetEntity dissovetEntity = this.dissovetRepository.findById(dissovetMemberEntity.getDisId()).orElse(null);
        UserEntity userEntity = this.userRepository.findById(dissovetEntity.getSecretaryId()).orElse(null);
        return DefaultDissovetMember.builder()
                .disId(dissovetMemberEntity.getDisId())
                .disInfo(dissovetEntity.getDisInfo())
                .memberType(dissovetMemberEntity.getMemberType())
                .disPosition(dissovetMemberEntity.getDisPosition())
                .disStartDate(dissovetEntity.getDisStartDate())
                .disStopDate(dissovetEntity.getDisStopDate())
                .userId(dissovetMemberEntity.getUserId())
                .ministryOrder(dissovetEntity.getMinistryOrder())
                .membersNum(dissovetEntity.getMembersNum())
                .secretaryId(dissovetEntity.getSecretaryId())
                .secretaryFirstName(userEntity.getFirstName())
                .secretaryLastName(userEntity.getLastName())
                .build();
    }

    private DefaultDissovet getDissovet(DissovetEntity dissovetEntity){
        List<DissovetMemberEntity> dissovetMemberEntities = this.dissovetMemberRepository.findByDisId(dissovetEntity.getDisId());
        return DefaultDissovet.builder()
                .disId(dissovetEntity.getDisId())
                .disInfo(dissovetEntity.getDisInfo())
                .disStartDate(dissovetEntity.getDisStartDate())
                .disStopDate(dissovetEntity.getDisStopDate())
                .ministryOrder(dissovetEntity.getMinistryOrder())
                .membersNum(dissovetEntity.getMembersNum())
                .secretaryId(dissovetEntity.getSecretaryId())
                .dissovetMembers(dissovetMemberEntities)
                .build();
    }
}
