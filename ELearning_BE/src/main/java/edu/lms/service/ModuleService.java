package edu.lms.service;

import edu.lms.dto.ModuleSearchRequest;
import edu.lms.dto.ModuleSummaryResponse;
import edu.lms.dto.PageResponse;

public interface ModuleService {

     PageResponse<ModuleSummaryResponse> getModulesByCourse(
            Long courseId,
            ModuleSearchRequest request,
            int page,
            int size
    );
}
