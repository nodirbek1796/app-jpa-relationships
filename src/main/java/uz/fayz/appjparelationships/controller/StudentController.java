package uz.fayz.appjparelationships.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.fayz.appjparelationships.entity.Student;
import uz.fayz.appjparelationships.repository.StudentRepository;

@RestController
@RequestMapping("/student")
public class StudentController {

    final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/forMinistry")
    public Page<Student> getStudentsListForMinistry(@RequestParam int page){
        Pageable pageable = PageRequest.of(page, 10);
        return studentRepository.findAll(pageable);
    }

    @GetMapping("/forUniversity/{universityId}")
    public Page<Student> getStudentsListForMinistry
            (
                    @RequestParam int page,
                    @PathVariable("universityId") Integer id
            )
    {
        Pageable pageable = PageRequest.of(page, 10);
        return studentRepository.findByGroup_Faculty_UniversityIdAnd(id, pageable);
    }
}
