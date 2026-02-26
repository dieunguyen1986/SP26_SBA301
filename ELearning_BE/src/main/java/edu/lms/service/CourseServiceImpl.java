package edu.lms.service;

import edu.lms.dto.CourseResponse;
import edu.lms.dto.CourseSummaryResponse;
import edu.lms.dto.PageResponse;
import edu.lms.entity.Course;
import edu.lms.enums.UploadFolder;
import edu.lms.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CloudinaryService cloudinaryService;

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
