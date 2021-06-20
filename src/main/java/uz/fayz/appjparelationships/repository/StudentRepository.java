package uz.fayz.appjparelationships.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.fayz.appjparelationships.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Page<Student> findByGroup_Faculty_UniversityIdAnd(Integer group_faculty_university_id, Pageable pageable);
}
