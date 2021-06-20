package uz.fayz.appjparelationships.controller;

import org.springframework.web.bind.annotation.*;
import uz.fayz.appjparelationships.entity.Subject;
import uz.fayz.appjparelationships.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class SubjectController {

    final
    SubjectRepository subjectRepository;

    public SubjectController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    // Read all subjects
    @GetMapping
    public List<Subject> getSubjects(){
        return subjectRepository.findAll();
    }

    @GetMapping("{id}")
    public Subject getSubject(@PathVariable Integer id){
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        return optionalSubject.orElseGet(Subject::new);
    }

    @PostMapping
    public String addSubject(@RequestBody Subject subject){
        if (subjectRepository.existsByName(subject.getName()))
            return "Following subject is already exists!";
        subjectRepository.save(subject);
        return "Subject successfully added!";
    }

    @PutMapping("{id}")
    public String updateSubject(@PathVariable Integer id, @RequestBody Subject subject){
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()){
            Subject subject1 = optionalSubject.get();
            subject1.setName(subject.getName());
            subjectRepository.save(subject1);
            return "Subject successfully edited!";
        }else
            return "Subject not found!";
    }

    @DeleteMapping("{id}")
    public String deleteSubject(@PathVariable Integer id){
        subjectRepository.deleteById(id);
        return "Subject successfully deleted!";
    }
}
