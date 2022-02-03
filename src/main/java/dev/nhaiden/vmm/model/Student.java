package dev.nhaiden.vmm.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class Student implements Serializable {
    @Id
    @NotBlank
    @Length(max = 8)
    public String id;

    @Column(name = "first_name")
    @NotBlank
    public String firstName;

    @Column(name = "last_name")
    @NotBlank
    public String lastName;

    @OneToMany(mappedBy = "student")
    @ToString.Exclude
    private List<Grade> grades;
}
