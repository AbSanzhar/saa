package kz.saa.prototype.services.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import kz.saa.prototype.models.entities.DepartmentsEntity;
import kz.saa.prototype.models.entities.RolesEntity;
import kz.saa.prototype.models.entities.UserEntity;
import kz.saa.prototype.models.pojos.impl.DefaultDepartment;
import kz.saa.prototype.models.pojos.json.DepartmentJson;
import kz.saa.prototype.repositories.DepartmentsRepository;
import kz.saa.prototype.repositories.RolesRepository;
import kz.saa.prototype.repositories.UserRepository;
import kz.saa.prototype.services.DepartmentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class DefaultDepartmentService implements DepartmentsService {

    @Autowired
    private DepartmentsRepository departmentsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public DefaultDepartment showDeptUsers(Long deptId) throws Exception{
        DepartmentsEntity departmentsEntity = this.departmentsRepository.findById(deptId).orElse(null);
        if (departmentsEntity == null) {
            throw new Exception("Department not found");
        }
        return DefaultDepartment.builder()
                .deptId(departmentsEntity.getDeptId())
                .deptName(departmentsEntity.getDeptName())
                .announcement(departmentsEntity.getAnnouncement())
                .headOfDept(departmentsEntity.getHeadOfDept())
                .usersDeptsEntities(departmentsEntity.getUsersDeptsEntities())
                .build();
    }

    @Override
    public String addDept(DepartmentJson departmentJson) {
        DepartmentsEntity departmentsEntity = new DepartmentsEntity();
        UserEntity userEntity = this.userRepository.findById(departmentJson.getHeadOfDept()).orElse(null);
        RolesEntity role = this.rolesRepository.selectByDept();
        List<RolesEntity> rolez = userEntity.getRoles();
        List<RolesEntity> roles = new ArrayList<>(rolez);
        roles.add(role);

        departmentsEntity.setDeptName(departmentJson.getDeptName());
        departmentsEntity.setHeadOfDept(departmentJson.getHeadOfDept());
        this.departmentsRepository.save(departmentsEntity);
        if (userEntity != null) {
            userEntity.setRoles(roles);
            this.userRepository.save(userEntity);
        }
        else {
            return "UserId not found";
        }
        log.info("Department {} added {}", departmentJson.getDeptName(), new Date());
        return "Department Added";
    }

    @Override
    public String addAnnouncement(Long deptId, String announcement) {
        DepartmentsEntity departmentsEntity = this.departmentsRepository.findById(deptId).orElse(null);
        if (departmentsEntity != null) {
            departmentsEntity.setAnnouncement(announcement);
            this.departmentsRepository.save(departmentsEntity);
            log.info("Announcement added for Department with ID : {} {}", deptId, new Date());
            return "Added announcement";
        }
        else
            log.info("Department with ID: {} Not Found {}", deptId, new Date());
            return "Department not found";

    }

}
