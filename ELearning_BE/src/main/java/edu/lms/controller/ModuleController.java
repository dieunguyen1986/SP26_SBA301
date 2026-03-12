package edu.lms.controller;

import edu.lms.constants.ApiPaths;
import edu.lms.dto.ModuleSearchRequest;
import edu.lms.dto.ModuleSummaryResponse;
import edu.lms.dto.PageResponse;
import edu.lms.service.ModuleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(ApiPaths.MY_COURSES)
public class ModuleController {

    @GetMapping("/{courseId}/modules")
    public PageResponse<ModuleSummaryResponse> getModules(
            @PathVariable Long courseId,
            ModuleSearchRequest request,
            @RequestParam int page,
            @RequestParam int size
    ) {

        return PageResponse.<ModuleSummaryResponse>builder().build();
    }
}
