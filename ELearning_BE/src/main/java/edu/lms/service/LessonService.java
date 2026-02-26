package edu.lms.service;

import edu.lms.dto.LessonRequest;
import edu.lms.dto.LessonResponse;
import edu.lms.entity.Lesson;
import org.springframework.web.multipart.MultipartFile;

public interface LessonService {
    LessonResponse createLesson(MultipartFile file, LessonRequest lessonRequest);

//    String uploadLesson(MultipartFile file);
}
