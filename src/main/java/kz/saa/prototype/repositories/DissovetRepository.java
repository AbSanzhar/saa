package kz.saa.prototype.repositories;

import kz.saa.prototype.models.entities.DissovetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DissovetRepository extends JpaRepository<DissovetEntity, Long> {

    List<DissovetEntity> findAllBySecretaryId(Long secretaryId);
}
