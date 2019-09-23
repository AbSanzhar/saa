package kz.saa.prototype.repositories;

import kz.saa.prototype.models.entities.DissovetEntity;
import kz.saa.prototype.models.entities.DissovetMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DissovetMemberRepository extends JpaRepository<DissovetMemberEntity, Long> {


    @Query(value = "SELECT * FROM dissovet_member d WHERE d.user_id=(SELECT u.user_id FROM users u WHERE u.user_id=:user_id)", nativeQuery = true)
    List<DissovetMemberEntity> findAllByUserId(@Param("user_id") Long userId);

    List<DissovetMemberEntity> findByDisId(Long disId);

}
