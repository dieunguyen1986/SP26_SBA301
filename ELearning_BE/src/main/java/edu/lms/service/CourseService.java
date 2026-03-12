package edu.lms.service;

import edu.lms.dto.*;
import edu.lms.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

public interface CourseService {

    PageResponse<CourseSummaryResponse> getAllCourses(Integer size, Integer page);

    public CourseResponse uploadThumbnail(Integer courseId, MultipartFile file);

    PageResponse<CourseDetailResponse> getAllCourseByInstructor(Integer page, Integer size, Authentication authentication);
    PageResponse<CourseDetailResponse> getAllCourseByInstructor(
            CourseSearchRequest request,
            int page,
            int size,
            Authentication authentication
    );
}
