package kz.saa.prototype.repositories;

import kz.saa.prototype.models.entities.ExternalUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExternalUserRepository extends JpaRepository<ExternalUserEntity, Long> {
    Optional<ExternalUserEntity> findByUserId(Long userId);
}
