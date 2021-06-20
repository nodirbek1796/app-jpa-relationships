package uz.fayz.appjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.fayz.appjparelationships.entity.Faculty;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
    boolean existsByNameAnAndUniversityId(String name, Integer universityId);
    List<Faculty> findByUniversityId(int universityId);
}
