package edu.lms.service;

import edu.lms.dto.CategoryRequest;
import edu.lms.dto.CategoryResponse;
import edu.lms.entity.Category;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest categoryRequest);

    List<CategoryResponse> getAllCategories();

    Category findCategoryById(Integer id);

    void deleteCategory(Integer id);

    CategoryResponse updateCategory(Integer id, CategoryRequest categoryRequest);



}
