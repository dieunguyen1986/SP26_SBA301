package edu.lms.repository;

import edu.lms.dto.ModuleSummaryResponse;
import edu.lms.entity.Module;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

    @Query("SELECT m " +
            "FROM Module m " +
            "WHERE m.course.courseId = :courseId")
    Page<Module> findByCourseId(Long courseId, Pageable pageable);


    @Query(" SELECT new edu.lms.dto.ModuleSummaryResponse( m.moduleId, m.title, m.description, m.sortOrder, m.status,  COUNT(l.lessonId),  m.createdAt) " +
            "FROM Module m LEFT JOIN m.lessons l " +
            "WHERE m.course.courseId = :courseId " +
            "GROUP BY m")
    Page<ModuleSummaryResponse> findModulesWithLessonCount(
            Long courseId,
            Pageable pageable
    );

}