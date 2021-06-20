package uz.fayz.appjparelationships.controller;

import org.springframework.web.bind.annotation.*;
import uz.fayz.appjparelationships.entity.Faculty;
import uz.fayz.appjparelationships.entity.Group;
import uz.fayz.appjparelationships.entity.University;
import uz.fayz.appjparelationships.payload.GroupDto;
import uz.fayz.appjparelationships.repository.FacultyRepository;
import uz.fayz.appjparelationships.repository.GroupRepository;
import uz.fayz.appjparelationships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupController {

    final GroupRepository groupRepository;
    final UniversityRepository universityRepository;
    final FacultyRepository facultyRepository;

    public GroupController(
            GroupRepository groupRepository,
            UniversityRepository universityRepository,
            FacultyRepository facultyRepository)
    {
        this.groupRepository = groupRepository;
        this.universityRepository = universityRepository;
        this.facultyRepository = facultyRepository;
    }

    /**
     * Vazirlik xodimi uchun read methodi
     * @return Groups list
     */
    @GetMapping
    public List<Group> getGroupsList(){
        return groupRepository.findAll();
    }

    @GetMapping("/byUniversityId/{universityId}")
    public List<Group> getGroupsListByUniversityId
            (
                    @PathVariable Integer universityId
            )
    {
        Optional<University> optionalUniversity = universityRepository.findById(universityId);
        if (!optionalUniversity.isPresent())
            return null;
        return groupRepository.findByFaculty_University(optionalUniversity.get());
    }

    @PostMapping
    public String addGroup(@RequestBody GroupDto groupDto){
        Group group = new Group();
        group.setName(groupDto.getName());
        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());
        if (!optionalFaculty.isPresent())
            return "Faculty not found!";
        group.setFaculty(optionalFaculty.get());
        groupRepository.save(group);
        return "Group successfully added!";
    }


}
