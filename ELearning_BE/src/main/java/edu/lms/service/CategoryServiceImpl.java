package edu.lms.service;

import edu.lms.dto.CategoryRequest;
import edu.lms.dto.CategoryResponse;
import edu.lms.entity.Category;
import edu.lms.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service("categoryService")
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;


    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {

        Integer id = checkInt(categoryRequest.getId(),"categoryId must be a positive integer");
        Integer parentId = checkInt(categoryRequest.getParentId(),"parentId must be a positive integer");
        Integer sortOrder =  checkInt(categoryRequest.getSortOrder(),"sortOrder must be a positive integer");

        Category existingCategory = categoryRepository.findById(id).orElse(null);

        Optional<Category> parentCategory = categoryRepository.findById(parentId);

        Category categoryRes = null;

        if (existingCategory != null) {
            existingCategory.setCategoryName(categoryRequest.getCategoryName());
            existingCategory.setDescription(categoryRequest.getDescription());
            existingCategory.setActive(categoryRequest.isActive());
            existingCategory.setSortOrder(sortOrder);
            existingCategory.setParent(parentCategory.isPresent() ? parentCategory.get() : null);
            existingCategory.setUpdateTime(LocalDateTime.now());

            categoryRes = categoryRepository.save(existingCategory);
        } else {

            Category category = Category.builder()
                    .categoryName(categoryRequest.getCategoryName())
                    .description(categoryRequest.getDescription())
                    .isActive(categoryRequest.isActive())
                    .sortOrder(Integer.parseInt(categoryRequest.getSortOrder()))
                    .updateTime(LocalDateTime.now())
                    .build();

            if (parentCategory.isPresent()) {
                category.setParent(parentCategory.get());
            }
            categoryRes = categoryRepository.save(category);
        }

        return CategoryResponse.builder()
                .categoryName(categoryRes.getCategoryName())
                .description(categoryRes.getDescription())
                .active(categoryRes.isActive())
                .updateTime(categoryRes.getUpdateTime())
                .build();
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAllCategories();
    }

    @Override
    public CategoryResponse findCategoryById(Integer id) {

         Category currentCategory =  categoryRepository.findById(id).orElseThrow(()->{
            throw  new IllegalArgumentException("Category not found");
        });

         return CategoryResponse.builder()
                 .id(currentCategory.getId())
                 .categoryName(currentCategory.getCategoryName())
                 .description(currentCategory.getDescription())
//                 .parentName(currentCategory.getParent().getCategoryName())
                 .parentId(currentCategory.getParent()!=null ?  currentCategory.getParent().getId() : null)
//                 .updateTime(currentCategory.getUpdateTime())
                 .sortOrder(currentCategory.getSortOrder())
                 .active(currentCategory.isActive())
                 .build();
    }

    @Override
    public void deleteCategory(Integer id) {

    }

    @Override
    public CategoryResponse updateCategory(Integer id, CategoryRequest categoryRequest) {
        return null;
    }

    private  Integer checkInt(String value, String message) {
        if(value!=null && !value.isBlank()){
            try {
                return  Integer.parseInt(value);
            } catch (Exception e) {

                log.error(message);
            }
        }
        return 0;
    }
}
