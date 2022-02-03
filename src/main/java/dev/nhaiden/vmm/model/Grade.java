package dev.nhaiden.vmm.model;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "grades")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Grade extends AbstractPersistable<Integer> implements Serializable {

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "subject")
    private String subject;

    @Column(name = "grade")
    @Max(value = 5)
    @Min(value = 1)
    private Integer gradeInt;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

}
