package kz.saa.prototype.repositories;

import kz.saa.prototype.models.entities.UserEntity;
import kz.saa.prototype.models.entities.UsersDeptsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByLoginEntityUsername(String username);

    List<UserEntity> findByUsersDeptsEntities(UsersDeptsEntity usersDeptsEntity);
}
