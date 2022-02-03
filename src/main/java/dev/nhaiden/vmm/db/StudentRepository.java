package dev.nhaiden.vmm.db;

import dev.nhaiden.vmm.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
}
