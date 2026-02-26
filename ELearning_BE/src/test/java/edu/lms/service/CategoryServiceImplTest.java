package edu.lms.service;

import edu.lms.dto.CategoryRequest;
import edu.lms.dto.CategoryResponse;
import edu.lms.entity.Category;
import static org.mockito.Mockito.when;
import edu.lms.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.annotation.Order;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@Slf4j
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Category categoryRes;

    private CategoryRequest categoryRequest;

    @BeforeAll
    static void beforeAll() {
        log.info("beforeAll");
    }

    @BeforeEach
    void setUp() {
        log.info("setUp");
        categoryRequest = new CategoryRequest();
        categoryRequest.setCategoryName("IT Services");
        categoryRequest.setActive(true);
        categoryRequest.setSortOrder("1");
        categoryRequest.setDescription("IT Services");

        categoryRes = new Category();
        categoryRes.setId(1);
        categoryRes.setCategoryName("IT Services");
        categoryRes.setUpdateTime(null);
        categoryRes.setActive(true);
        categoryRes.setSortOrder(1);
        categoryRes.setDescription("IT Services");

    }

    @AfterEach
    void tearDown() {
        log.info("tearDown");
    }


    /**
     * Happy Case
     */
    @Test
    @Order(1)
    void createCategory1() {
        when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(categoryRes);


        CategoryResponse actualResult = categoryService.createCategory(categoryRequest);

        assertNotNull(actualResult);
        assertEquals(categoryRes.getCategoryName(), actualResult.getCategoryName());
    }

    @Test
    void createCategory2() {
//        when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(categoryRes);

        log.info("createCategory2");
    }

    @Test
    void getAllCategories() {
    }

    @Test
    void findCategoryById() {
    }

    @Test
    void deleteCategory() {
    }

    @Test
    void updateCategory() {
    }

    @AfterAll
    static void afterAll() {
        log.info("afterAll");
    }
}