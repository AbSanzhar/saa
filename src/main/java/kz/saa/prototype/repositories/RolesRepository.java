package kz.saa.prototype.repositories;

import kz.saa.prototype.models.entities.RolesEntity;
import kz.saa.prototype.models.pojos.impl.DefaultUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, Long> {

    @Query(value = "select * from roles where role_name='Dissovet_Member'", nativeQuery = true)
    RolesEntity selectByDis();

    @Query(value = "select * from roles where role_name='Head_Of_Dept'", nativeQuery = true)
    RolesEntity selectByDept();
}
