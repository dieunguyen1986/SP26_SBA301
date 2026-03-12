package edu.lms.repository;

import edu.lms.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>, JpaSpecificationExecutor<Course> {

    @Query("SELECT c FROM Course c INNER JOIN c.creator ct WHERE ct.id = :instructorId")
    Page<Course> findAllByInstructor(Pageable pageable, Long instructorId);

    @EntityGraph(attributePaths = {"courseCategories.category"})
    Page<Course> findAll(Specification<Course> spec, Pageable pageable);

}
