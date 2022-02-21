package dev.nhaiden.vmm.db;

import dev.nhaiden.vmm.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
}
