package edu.lms.controller;


import edu.lms.constants.ApiPaths;
import edu.lms.dto.CategoryRequest;

import edu.lms.dto.CategoryResponse;
import edu.lms.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(ApiPaths.CATEGORIES)
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173"})
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
        categoryService.createCategory(categoryRequest);

        return ResponseEntity.ok().body(Map.of("message", "Category created"));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getCategories() {
        return ResponseEntity.ok().body(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable Integer id) {

                 return ResponseEntity.ok().body(categoryService.findCategoryById(id));
    }

}
