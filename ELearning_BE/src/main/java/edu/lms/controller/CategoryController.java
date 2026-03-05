package edu.lms.controller;


import edu.lms.constants.ApiPaths;
import edu.lms.dto.CategoryRequest;

import edu.lms.dto.CategoryResponse;
import edu.lms.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(ApiPaths.CATEGORIES)
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173"})
@Tags({@Tag(name = "Category APIs", description = "All of the API for category")})
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    @Operation(method = "POST", tags = {"Category APIs"},
            summary = "The endpoint to create a new category into db",
            description = "The endpoint to create a new category into db",
            requestBody =  @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "The DTO object wwith .....",
                    required = true,
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {
                                    @ExampleObject(
                                            name = "CategoryRequest",
                                            summary = "Category Request Body",
                                            value = "{\n" +
                                                    "  \"categoryName\": \"IT & Software\",\n" +
                                                    "  \"description\": \"Courses related to programming, development, and IT skills\",\n" +
                                                    "  \"isActive\": true,\n" +
                                                    "  \"sortOrder\": \"1\"\n" +
                                                    "}"
                                    )
                            }

                    )})
    )
    public ResponseEntity<?> createCategory(HttpSession session, @RequestBody @Valid CategoryRequest categoryRequest) {
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
