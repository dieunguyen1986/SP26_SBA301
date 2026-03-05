package edu.lms.controller;

import edu.lms.constants.ApiPaths;
import edu.lms.dto.CourseDetailResponse;
import edu.lms.dto.CourseResponse;
import edu.lms.dto.CourseSummaryResponse;
import edu.lms.dto.PageResponse;
import edu.lms.service.CourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Tag(name = "Course APIs")
public class CourseController {

    private final CourseService courseService; // DI

    @GetMapping(ApiPaths.COURSES)
    public PageResponse<CourseSummaryResponse> getAllCourses(
            @RequestParam int page,
            @RequestParam int size
    ) {
        return courseService.getAllCourses(page, size);
    }

    @PostMapping("/{courseId}/thumbnail")
    public ResponseEntity<CourseResponse> uploadThumbnail(
            @PathVariable Integer courseId,
            @RequestParam("file") MultipartFile file
    ) {
        return ResponseEntity.ok(
                courseService.uploadThumbnail(courseId, file)
        );
    }

    @GetMapping(ApiPaths.MY_COURSES)
    public PageResponse<CourseDetailResponse> getCourseByInstructor(
            @RequestParam int page,
            @RequestParam int size,
            Authentication authentication
    ) {
        return courseService.getAllCourseByInstructor(page, size, authentication);
    }
}
