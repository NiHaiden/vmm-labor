package dev.nhaiden.vmm.db;

import dev.nhaiden.vmm.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Integer> {

    List<Grade> findAllByStudentId(String id);
}
