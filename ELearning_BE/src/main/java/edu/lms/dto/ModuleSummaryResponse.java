package edu.lms.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModuleSummaryResponse {

    private Long moduleId;

    private String title;

    private String description;

    private int sortOrder;

    private String status;

    private long lessonCount;

    private LocalDateTime createdAt;

}