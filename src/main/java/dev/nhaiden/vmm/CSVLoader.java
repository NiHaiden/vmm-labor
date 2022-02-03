package dev.nhaiden.vmm;

import dev.nhaiden.vmm.db.GradeRepository;
import dev.nhaiden.vmm.db.StudentRepository;
import dev.nhaiden.vmm.model.Grade;
import dev.nhaiden.vmm.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

import static java.lang.System.out;

@Component
public class CSVLoader implements CommandLineRunner {


    @Autowired
    public StudentRepository studentRepository;

    @Autowired
    public GradeRepository gradeRepository;

    @Override
    public void run(String... args) throws Exception {

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(getGradesFile()))) {
            String line;
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                out.println(line);
                gradeRepository.save(fromCSV(line));
            }
        }
    }

    public File getGradesFile() throws IOException {
        return new ClassPathResource("grades.csv").getFile();
    }

    public Grade fromCSV(String csvLine) {
        out.println("CSV LINE: " + csvLine);
        String parts[] = csvLine.split(",");
        String studentID = parts[0];
        LocalDate assignedDate = LocalDate.parse(parts[1]);
        String subject = parts[2];
        Integer gradeNum = Integer.parseInt(parts[3]);

        Student student = studentRepository.findById(studentID).orElseThrow(() -> {
            throw new IllegalArgumentException("Student was not found!");
        });

        Grade grade = new Grade();
        grade.setGradeInt(gradeNum);
        grade.setDate(assignedDate);
        grade.setSubject(subject);
        grade.setStudent(student);

        return grade;
    }
}
