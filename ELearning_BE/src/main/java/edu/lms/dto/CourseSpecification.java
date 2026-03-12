package edu.lms.dto;

import edu.lms.entity.Category;
import edu.lms.entity.Course;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CourseSpecification {

    public static Specification<Course> searchInstructorCourse(
            String instructorEmail,
            CourseSearchRequest request
    ) {

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.equal(
                    root.get("creator").get("email"),
                    instructorEmail
            ));

            if (request.getKeyword() != null && !request.getKeyword().isEmpty()) {

                predicates.add(cb.like(
                        cb.lower(root.get("title")),
                        "%" + request.getKeyword().toLowerCase() + "%"
                ));
            }

            if (request.getStatus() != null && !request.getStatus().isEmpty()) {

                predicates.add(cb.equal(
                        root.get("status"),
                        request.getStatus()
                ));
            }

            if (request.getCategoryId() != null) {

                Join<Course, Category> join = root.join("categories");

                predicates.add(cb.equal(
                        join.get("id"),
                        request.getCategoryId()
                ));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}