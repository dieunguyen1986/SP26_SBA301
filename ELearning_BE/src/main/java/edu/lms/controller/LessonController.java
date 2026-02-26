package edu.lms.controller;

import edu.lms.constants.ApiPaths;
import edu.lms.constants.BaseURI;
import edu.lms.dto.LessonRequest;
import edu.lms.entity.Lesson;
import edu.lms.enums.LessonType;
import edu.lms.service.LessonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping(BaseURI.API_V1)
@Slf4j
public class LessonController {

    private final LessonService lessonService;

    @PostMapping
    public Lesson createLesson(@RequestPart("data") Lesson lesson,
                               @RequestPart("file") MultipartFile file) {
        return null;
    }

    @PostMapping("/{moduleId}/lessons")
    public Lesson createLesson2(@PathVariable String moduleId,
                               @RequestParam String title,
                               @RequestParam String lessonType,
                               @RequestParam Integer durationSeconds,
                               @RequestParam Boolean isPreview,
                               @RequestParam Integer sortOrder,
                               @RequestParam  String status,
                               @RequestPart("file") MultipartFile file) {

        log.info("Creating Lesson Request");

        LessonRequest lessonRequest = LessonRequest.builder()
                .title(title)
                .lessonType(lessonType)
                .durationSeconds(durationSeconds)
                .status(status)
                .isPreview(isPreview)
                .sortOrder(sortOrder).build();

        lessonService.createLesson(file, lessonRequest);

        return null;
    }



}
