package br.com.compassuol.pb.challenge.productservice;

import br.com.compassuol.pb.challenge.productservice.controller.CategoryController;
import br.com.compassuol.pb.challenge.productservice.dto.CategoryRequest;
import br.com.compassuol.pb.challenge.productservice.dto.CategoryResponse;
import br.com.compassuol.pb.challenge.productservice.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CategoryControllerTest {
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        categoryController = new CategoryController(categoryService);
    }

    @Test
    public void testCreateCategory() {
        // Arrange
        CategoryRequest categoryRequest = new CategoryRequest("New Category");
        String successMessage = "Categoria criada com sucesso!";

        // Act
        String response = categoryController.createCategory(categoryRequest);

        // Assert
        assertEquals(successMessage, response);
        verify(categoryService, times(1)).createCategory(categoryRequest);
    }

    @Test
    public void testGetAllCategories() {
        // Arrange
        CategoryResponse category1 = new CategoryResponse("1", "Category 1");
        CategoryResponse category2 = new CategoryResponse("2", "Category 2");
        List<CategoryResponse> expectedCategories = Arrays.asList(category1, category2);

        when(categoryService.getAllCategories()).thenReturn(expectedCategories);

        // Act
        List<CategoryResponse> response = categoryController.getAllCategories();

        // Assert
        assertEquals(expectedCategories, response);
        verify(categoryService, times(1)).getAllCategories();
    }

    @Test
    public void testGetCategoryById() {
        // Arrange
        String categoryId = "1";
        CategoryResponse expectedCategory = new CategoryResponse(categoryId, "Category 1");

        when(categoryService.getCategoryById(categoryId)).thenReturn(expectedCategory);

        // Act
        CategoryResponse response = categoryController.getCategoryById(categoryId);

        // Assert
        assertEquals(expectedCategory, response);
        verify(categoryService, times(1)).getCategoryById(categoryId);
    }

    @Test
    public void testUpdateCategory() {
        // Arrange
        String categoryId = "1";
        CategoryRequest categoryRequest = new CategoryRequest("Updated Category");
        String successMessage = "Categoria atualizada com sucesso!";

        // Act
        String response = categoryController.updateCategory(categoryId, categoryRequest);

        // Assert
        assertEquals(successMessage, response);
        verify(categoryService, times(1)).updateCategory(categoryId, categoryRequest);
    }

    @Test
    public void testDeleteCategory() {
        // Arrange
        String categoryId = "1";
        String successMessage = "Categoria deletada com sucesso!";

        // Act
        String response = categoryController.deleteCategory(categoryId);

        // Assert
        assertEquals(successMessage, response);
        verify(categoryService, times(1)).deleteCategory(categoryId);
    }
}
