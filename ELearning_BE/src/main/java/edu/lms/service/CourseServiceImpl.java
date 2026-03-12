package edu.lms.service;

import edu.lms.dto.*;
import edu.lms.entity.*;
import edu.lms.enums.UploadFolder;
import edu.lms.repository.CourseRepository;
import edu.lms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CloudinaryService cloudinaryService;
    private final UserRepository userRepository;

    @Override
    public PageResponse<CourseSummaryResponse> getAllCourses(Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page - 1, size); // UI thường bắt đầu từ 1

        Page<Course> coursePage = courseRepository.findAll(pageable);

        List<CourseSummaryResponse> content = coursePage.getContent()
                .stream()
                .map(this::toSummary)
                .toList();

        return PageResponse.<CourseSummaryResponse>builder()
                .content(content)
                .page(page)
                .size(size)
                .totalElements(coursePage.getTotalElements())
                .totalPages(coursePage.getTotalPages())
                .build();
    }

    @Override
    public CourseResponse uploadThumbnail(Integer courseId, MultipartFile file) {
        String uploadResult = cloudinaryService.uploadFile(file, UploadFolder.COURSE, "courseId");

        log.info("upload result {}", uploadResult);

        return null;
    }

    public CourseDetailResponse getCourseDetail(Integer courseId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        return mapToResponse(course);
    }

    @Override
    public PageResponse<CourseDetailResponse> getAllCourseByInstructor(Integer page, Integer size, Authentication authentication) {
//        String username = ((UserDetails)authentication.getDetails()).getUsername();
        String username = "instructor@lms.com";

        Long instructorId = userRepository.findByEmail(username).get().getId();

        log.info("get all course by instructor {}", instructorId);

        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Course> coursePage = courseRepository.findAllByInstructor(pageable, instructorId);


        List<CourseDetailResponse> content = coursePage.getContent()
                .stream()
                .map(this::mapToResponse)
                .toList();

        return PageResponse.<CourseDetailResponse>builder()
                .content(content)
                .page(coursePage.getNumber())
                .size(coursePage.getSize())
                .totalElements(coursePage.getTotalElements())
                .totalPages(coursePage.getTotalPages())
                .build();
    }

    public PageResponse<CourseDetailResponse> getAllCourseByInstructor(
            CourseSearchRequest request,
            int page,
            int size,
            Authentication authentication
    ) {

//        String email = authentication.getName();
        String email = "instructor@lms.com";
        Pageable pageable = PageRequest.of(page, size);

        Specification<Course> spec = CourseSpecification.searchInstructorCourse(email, request);

        Page<Course> coursePage = courseRepository.findAll(spec, pageable);

        return PageResponse.from(coursePage.map(this::mapToResponse));
    }


    public CourseDetailResponse mapToResponse(Course course) {
        if (course == null) return null;

        return CourseDetailResponse.builder()
                .courseId(course.getCourseId())
                .title(course.getTitle())
                .subtitle(course.getSubtitle())
                .description(course.getDescription())
                .shortDescription(course.getShortDescription())
                .thumbnailUrl(course.getThumbnailUrl())
                .courseLevel(course.getCourseLevel())
                .language(course.getLanguage())
                .price(course.getPrice())
                .discount(course.getDiscount())
                .rating(course.getRating())
                .ratingCount(course.getRatingCount())
                .students(course.getStudents())
                .totalHours(course.getTotalHours())
                .status(course.getStatus())
                .publishAt(course.getPublishAt())

                // KEY PART: many-to-many mapping
                .categories(mapCategories(course.getCourseCategories()))

                .build();
    }

    private Set<CategoryResponse> mapCategories(Set<CourseCategory> courseCategories) {
        if (courseCategories == null || courseCategories.isEmpty()) {
            return Collections.emptySet();
        }

        return courseCategories.stream()
                .map(CourseCategory::getCategory)   // lấy Category
                .filter(Objects::nonNull)
                .map(this::mapCategory)            // map sang response
                .collect(Collectors.toSet());
    }


    private CategoryResponse mapCategory(Category category) {
        if (category == null) return null;

        return CategoryResponse.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .build();
    }

    private CourseSummaryResponse toSummary(Course c) {
        return CourseSummaryResponse.builder()
                .courseId(c.getCourseId())
                .title(c.getTitle())
                .subtitle(c.getSubtitle())
                .thumbnailUrl(c.getThumbnailUrl())
                .price(c.getPrice())
                .discount(c.getDiscount())
                .rating(c.getRating())
                .ratingCount(c.getRatingCount())
                .students(c.getStudents())
                .totalHours(c.getTotalHours())
                .level(c.getCourseLevel() != null ? c.getCourseLevel().name() : null)
                .language(c.getLanguage())
                .build();
    }
}
