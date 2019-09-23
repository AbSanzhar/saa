package kz.saa.prototype.repositories;

import kz.saa.prototype.models.entities.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Long> {
    List<LoginEntity> findByUserEntityUserIdIsNotNull();

    @Query("select l from LoginEntity l where l.username=?1 and l.password=?2")
    LoginEntity findByUsernameAndPassword(String username, String password);

    @Query("select l from LoginEntity l where l.username=?1")
    Optional<LoginEntity> findByUsername(String username);

}
