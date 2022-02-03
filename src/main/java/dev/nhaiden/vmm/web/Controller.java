package dev.nhaiden.vmm.web;

import dev.nhaiden.vmm.db.GradeRepository;
import dev.nhaiden.vmm.db.StudentRepository;
import dev.nhaiden.vmm.model.Grade;
import dev.nhaiden.vmm.model.Student;
import dev.nhaiden.vmm.model.Subjects;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping("/students")
public class Controller {

    StudentRepository studentRepository;
    GradeRepository gradeRepository;

    public Controller(StudentRepository studentRepository, GradeRepository gradeRepository) {
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
    }

    @GetMapping("/all")
    public String all(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "students/all";
    }

    @GetMapping("/grades/{id}")
    public String allGradesForStudent(Model model, @PathVariable String id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("ID is invalid!");
        });

        model.addAttribute("student", student);
        model.addAttribute("subjects", Subjects.values());
        model.addAttribute("gradeNums", List.of(1,2,3,4,5));
        model.addAttribute("grade", new Grade());

        return "students/grades";
    }
    @PostMapping("/grades/{id}")
    public String addNewGrade(Model model, @PathVariable String id, @Valid Grade grade, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return allGradesForStudent(model, id);
        }
        Student student = studentRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("ID is invalid!");
        });
        grade.setStudent(student);
        gradeRepository.save(grade);

        return "redirect:/students/grades/"+id;
    }
}
