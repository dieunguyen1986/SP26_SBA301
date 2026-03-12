package edu.lms.repository;

import edu.lms.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    @Query("SELECT COUNT(l) " +
            "FROM Lesson l " +
            "WHERE l.module.moduleId = :moduleId")
    int countByModuleId(Long moduleId);
}
