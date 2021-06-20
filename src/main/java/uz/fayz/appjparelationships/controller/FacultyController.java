package uz.fayz.appjparelationships.controller;

import org.springframework.web.bind.annotation.*;
import uz.fayz.appjparelationships.entity.Faculty;
import uz.fayz.appjparelationships.entity.University;
import uz.fayz.appjparelationships.payload.FacultyDto;
import uz.fayz.appjparelationships.repository.FacultyRepository;
import uz.fayz.appjparelationships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/faculty")
public class FacultyController {

    final
    FacultyRepository facultyRepository;
    final
    UniversityRepository universityRepository;

    public FacultyController(FacultyRepository facultyRepository, UniversityRepository universityRepository) {
        this.facultyRepository = facultyRepository;
        this.universityRepository = universityRepository;
    }

    @PostMapping
    public String addFaculty(
            @RequestBody FacultyDto facultyDto
    ) {
        boolean exists = facultyRepository.existsByNameAnAndUniversityId(facultyDto.getName(), facultyDto.getUniversityId());
        if (exists)
            return "This faculty already added to this university!";

        Faculty faculty = new Faculty();
        faculty.setName(facultyDto.getName());

        Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
        if (optionalUniversity.isPresent()) {
            faculty.setUniversity(optionalUniversity.get());
            facultyRepository.save(faculty);
            return "Faculty successfully added!";
        }else
            return "University not found!";
    }

    @GetMapping
    public List<Faculty> getFaculties(){
        return facultyRepository.findAll();
    }

    @GetMapping("/byUniversityId/{universityId}")
    public List<Faculty> getFacultyByUniversityId(@PathVariable Integer universityId){
        return facultyRepository.findByUniversityId(universityId);
    }

    @DeleteMapping("/{facultyId}")
    public String deleteFaculty(@PathVariable Integer facultyId){
        try {
            facultyRepository.deleteById(facultyId);
            return "Faculty successfully deleted!";
        }catch (Exception exception){
            return "Error occurred with deleting faculty!";
        }
    }

    @PutMapping("/{id}")
    public String editFaculty(
            @PathVariable("id") Integer facultyId,
            @RequestBody FacultyDto facultyDto)
    {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(facultyId);
        if (optionalFaculty.isPresent()){
            Faculty faculty = optionalFaculty.get();
            faculty.setName(facultyDto.getName());

            Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
            if (!optionalUniversity.isPresent())
                return "University not found!";

            University university = optionalUniversity.get();
            faculty.setUniversity(university);
            facultyRepository.save(faculty);
            return "Faculty successfully edited!";
        }
        return "Faculty not found!";
    }
}
