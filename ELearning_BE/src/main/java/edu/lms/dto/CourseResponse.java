package edu.lms.dto;

import edu.lms.enums.CourseLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponse {

    private Integer id;
    private String title;
    private String subtitle;
    private String description;
    private String thumbnailUrl;

    private Double price;
    private Double discount;

    private Double rating;
    private Integer students;

    private List<ModuleResponse> modules;
}