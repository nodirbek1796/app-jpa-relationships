package uz.fayz.appjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.fayz.appjparelationships.entity.University;

public interface UniversityRepository extends JpaRepository<University, Integer> {
}
