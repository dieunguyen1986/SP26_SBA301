package edu.lms.service;

import edu.lms.dto.ModuleSummaryResponse;
import edu.lms.entity.Module;
import edu.lms.repository.LessonRepository;
import edu.lms.repository.ModuleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ModuleServiceImpl {
    private final ModuleRepository moduleRepository;
    private final LessonRepository lessonRepository;

    private ModuleSummaryResponse mapToSummary(Module module) {

        int lessonCount = lessonRepository.countByModuleId(module.getModuleId());

        return ModuleSummaryResponse.builder()
                .moduleId(module.getModuleId())
                .title(module.getTitle())
                .description(module.getDescription())
                .sortOrder(module.getSortOrder())
                .status(module.getStatus())
                .lessonCount(lessonCount)
                .createdAt(module.getCreatedAt())
                .build();
    }
}
