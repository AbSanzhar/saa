package kz.saa.prototype.repositories;

import kz.saa.prototype.models.entities.DepartmentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepartmentsRepository extends JpaRepository<DepartmentsEntity, Long> {
}
