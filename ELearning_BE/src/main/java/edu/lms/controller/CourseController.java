package edu.lms.controller;

import edu.lms.constants.ApiPaths;
import edu.lms.dto.*;
import edu.lms.service.CourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Tag(name = "Course APIs")
@Slf4j
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

//    @GetMapping(ApiPaths.MY_COURSES)
//    public PageResponse<CourseDetailResponse> getCourseByInstructor(
//            @RequestParam int page,
//            @RequestParam int size,
//            Authentication authentication
//    ) {
//        return courseService.getAllCourseByInstructor(page, size, authentication);
//    }


    @GetMapping(ApiPaths.MY_COURSES)
    public PageResponse<CourseDetailResponse> getCourseByInstructor(
            CourseSearchRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Authentication authentication
    ) {
        log.info("In CourseController.getCourseByInstructor {}", request);

        return courseService.getAllCourseByInstructor(request, page, size, authentication);
    }
}
