package edu.lms.service;

import edu.lms.dto.LessonRequest;
import edu.lms.dto.LessonResponse;
import edu.lms.entity.Lesson;
import edu.lms.enums.LessonType;
import edu.lms.enums.UploadFolder;
import edu.lms.mapper.LessonMapper;
import edu.lms.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class LessonServiceImpl implements LessonService {
    private final CloudinaryService cloudinaryService;
    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;

    @Override
    public LessonResponse createLesson(MultipartFile file, LessonRequest lessonRequest) {
        // Call Cloudinary Service

        String contentUrl = cloudinaryService.uploadFile(file, UploadFolder.LESSON, "lessonId");

        log.info("Content URL {}", contentUrl);

        // Save to DB - repo

        Lesson lesson = lessonMapper.toEntity(lessonRequest);
        lesson.setContentURL(contentUrl);
        lesson.setCreatedAt(LocalDateTime.now());
        lesson.setUpdatedAt(LocalDateTime.now());
        lesson.setLessonType(LessonType.valueOf(lessonRequest.getLessonType()));

        Lesson result = lessonRepository.save(lesson);


        return lessonMapper.toResDto(result);
    }


}
