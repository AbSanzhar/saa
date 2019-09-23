package kz.saa.prototype.repositories;

import kz.saa.prototype.models.entities.DepartmentsEntity;
import kz.saa.prototype.models.entities.UserEntity;
import kz.saa.prototype.models.entities.UsersDeptsEntity;
import kz.saa.prototype.models.entities.UsersDeptsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersDeptsRepository extends JpaRepository<UsersDeptsEntity, UsersDeptsId> {

    @Query(value = "SELECT users_depts.user_id, users_depts.dept_id, users_depts.user_type FROM users_depts WHERE user_id=?1", nativeQuery = true)
    List<UsersDeptsEntity> selectByUserId(Long userId);

}
