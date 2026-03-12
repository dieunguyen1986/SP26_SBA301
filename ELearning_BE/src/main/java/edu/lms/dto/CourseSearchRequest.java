package edu.lms.dto;


import lombok.Data;

@Data
public class CourseSearchRequest {

    private String keyword;

    private String status;

    private Integer categoryId;

    private String sortBy = "updatedAt";

    private String sortDir = "desc";

}