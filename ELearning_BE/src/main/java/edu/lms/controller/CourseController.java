package edu.lms.controller;

import edu.lms.constants.ApiPaths;
import edu.lms.dto.CourseResponse;
import edu.lms.dto.CourseSummaryResponse;
import edu.lms.dto.PageResponse;
import edu.lms.service.CourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.COURSES)
@Tag(name = "Course APIs")
public class CourseController {

    private final CourseService courseService; // DI

    @GetMapping
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
}
