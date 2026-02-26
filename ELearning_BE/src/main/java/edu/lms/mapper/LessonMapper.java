package edu.lms.mapper;

import edu.lms.dto.LessonRequest;
import edu.lms.dto.LessonResponse;
import edu.lms.entity.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LessonMapper {
    @Mapping(target = "lessonType", ignore = true)
    Lesson toEntity(LessonRequest lessonRequest);

    LessonResponse toResDto(Lesson lesson);
    LessonRequest toReqDto(Lesson lesson);
}
