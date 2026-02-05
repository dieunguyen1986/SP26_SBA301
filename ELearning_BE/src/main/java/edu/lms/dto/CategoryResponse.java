package edu.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class CategoryResponse {
    private Integer id;
    private String categoryName;
    private String description;
    private boolean active;
    private int sortOrder;;


    private LocalDateTime updateTime;

    private String parentName;
    private Integer parentId;


}
