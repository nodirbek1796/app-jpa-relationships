package uz.fayz.appjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.fayz.appjparelationships.entity.Group;
import uz.fayz.appjparelationships.entity.University;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Integer> {
    List<Group> findByFaculty_University(University faculty_university);
}
