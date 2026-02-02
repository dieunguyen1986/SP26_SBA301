package edu.lms.service;

import edu.lms.dto.CategoryRequest;
import edu.lms.dto.CategoryResponse;
import edu.lms.entity.Category;
import edu.lms.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("categoryService")
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {

        Optional<Category> optional = categoryRepository.findById(categoryRequest.getParentId());


        Category category = Category.builder()
                .categoryName(categoryRequest.getCategoryName())
                .description(categoryRequest.getDescription())
                .isActive(categoryRequest.isActive())
                .sortOrder(categoryRequest.getSortOrder())
                .build();

        if (optional.isPresent()) {
            category.setParent(optional.get());
        }

        Category result = categoryRepository.save(category);

        return CategoryResponse.builder()
                .categoryName(result.getCategoryName())
                .description(result.getDescription())
                .isActive(result.isActive())
                .updateTime(result.getUpdateTime())
                .build();
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAllCategories();
    }

    @Override
    public Category findCategoryById(Integer id) {
        return null;
    }

    @Override
    public void deleteCategory(Integer id) {

    }

    @Override
    public CategoryResponse updateCategory(Integer id, CategoryRequest categoryRequest) {
        return null;
    }
}
