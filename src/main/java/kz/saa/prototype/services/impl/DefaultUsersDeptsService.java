package kz.saa.prototype.services.impl;

import kz.saa.prototype.models.entities.DepartmentsEntity;
import kz.saa.prototype.models.entities.UserEntity;
import kz.saa.prototype.models.entities.UsersDeptsEntity;
import kz.saa.prototype.models.pojos.UsersDepts;
import kz.saa.prototype.models.pojos.impl.DefaultUsersDepts;
import kz.saa.prototype.models.pojos.json.UsersDeptsJson;
import kz.saa.prototype.repositories.DepartmentsRepository;
import kz.saa.prototype.repositories.UserRepository;
import kz.saa.prototype.repositories.UsersDeptsRepository;
import kz.saa.prototype.services.UsersDeptsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class DefaultUsersDeptsService implements UsersDeptsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentsRepository departmentsRepository;

    @Autowired
    private UsersDeptsRepository usersDeptsRepository;

    @Override
    public List<UsersDepts> showUsersDepts(Long userId) {
        List<UsersDeptsEntity> usersDeptsEntities = this.usersDeptsRepository.selectByUserId(userId);
        return usersDeptsEntities.stream().map(usersDeptsEntity -> DefaultUsersDepts.builder()
            .userId(usersDeptsEntity.getPrimaryKey().getUserEntity().getUserId())
            .deptId(usersDeptsEntity.getPrimaryKey().getDepartmentsEntity().getDeptId())
            .userType(usersDeptsEntity.getUserType())
        .build()).collect(Collectors.toList());
    }

    @Override
    public String addUsersDepts(UsersDeptsJson usersDeptsJson) {
        UserEntity userEntity = this.userRepository.findById(usersDeptsJson.getUserId()).orElse(null);
        DepartmentsEntity departmentsEntity = this.departmentsRepository.findById(usersDeptsJson.getDeptId()).orElse(null);

        UsersDeptsEntity usersDeptsEntity = new UsersDeptsEntity();
        usersDeptsEntity.setUserEntity(userEntity);
        usersDeptsEntity.setDepartmentsEntity(departmentsEntity);
        usersDeptsEntity.setUserType(usersDeptsJson.getUserType());
        this.usersDeptsRepository.save(usersDeptsEntity);

        log.info("Added new Users Depts {}", new Date());
        return "added";
    }

//    @Override
//    public void updateUsersDepts(UsersDeptsJson usersDeptsJson) {
//        Long userId = usersDeptsJson.getUserId();
//        Long deptId = usersDeptsJson.getDeptId();
//        UserEntity userEntity = this.userRepository.findById(userId).orElse(null);
//        DepartmentsEntity departmentsEntity = this.departmentsRepository.findById(deptId).orElse(null);
//        UsersDeptsEntity usersDeptsEntity = this.usersDeptsRepository.findByUserEntityAndDepartmentsEntity(userEntity, departmentsEntity);
//        if (Objects.nonNull(usersDeptsEntity)) {
//            if (usersDeptsJson.getUserId() != null)
//                usersDeptsEntity.getUserEntity().setUserId(usersDeptsJson.getUserId());
//            if (usersDeptsJson.getDeptId() != null)
//                usersDeptsEntity.getDepartmentsEntity().setDeptId(usersDeptsJson.getDeptId());
//            if (usersDeptsJson.getUserType() != null)
//                usersDeptsEntity.setUserType(usersDeptsJson.getUserType());
//            usersDeptsEntity.setUserEntity(userEntity);
//            usersDeptsEntity.setDepartmentsEntity(departmentsEntity);
//            this.usersDeptsRepository.save(usersDeptsEntity);
//            log.info("Updated new Users Depts . {}", new Date());
//        }
//    }
}
