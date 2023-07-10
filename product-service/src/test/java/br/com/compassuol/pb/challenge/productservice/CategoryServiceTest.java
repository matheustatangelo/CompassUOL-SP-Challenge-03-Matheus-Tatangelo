package br.com.compassuol.pb.challenge.productservice;

import br.com.compassuol.pb.challenge.productservice.dto.CategoryRequest;
import br.com.compassuol.pb.challenge.productservice.dto.CategoryResponse;
import br.com.compassuol.pb.challenge.productservice.model.Category;
import br.com.compassuol.pb.challenge.productservice.repository.CategoryRepository;
import br.com.compassuol.pb.challenge.productservice.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {

    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    public void testCreateCategory() {
        // Arrange
        CategoryRequest categoryRequest = new CategoryRequest("New Category");

        // Act
        categoryService.createCategory(categoryRequest);

        // Assert
        ArgumentCaptor<Category> categoryCaptor = ArgumentCaptor.forClass(Category.class);
        verify(categoryRepository, times(1)).save(categoryCaptor.capture());
        Category capturedCategory = categoryCaptor.getValue();
        assertEquals(categoryRequest.getName(), capturedCategory.getName());
    }

    @Test
    public void testUpdateCategory() {
        // Arrange
        String categoryId = "1";
        CategoryRequest categoryRequest = new CategoryRequest("Updated Category");
        Category existingCategory = new Category(categoryId, "Old Category");
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(existingCategory));
        when(categoryRepository.save(any(Category.class))).thenReturn(existingCategory);

        // Act
        categoryService.updateCategory(categoryId, categoryRequest);

        // Assert
        verify(categoryRepository, times(1)).findById(categoryId);
        verify(categoryRepository, times(1)).save(existingCategory);
        assertEquals(categoryRequest.getName(), existingCategory.getName());
    }

    @Test
    public void testDeleteCategory() {
        // Arrange
        String categoryId = "1";
        Category existingCategory = new Category(categoryId, "Existing Category");
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(existingCategory));
        doNothing().when(categoryRepository).delete(existingCategory);

        // Act
        categoryService.deleteCategory(categoryId);

        // Assert
        verify(categoryRepository, times(1)).findById(categoryId);
        verify(categoryRepository, times(1)).delete(existingCategory);
    }

    @Test
    public void testGetAllCategories() {
        // Arrange
        Category category1 = new Category("1", "Category 1");
        Category category2 = new Category("2", "Category 2");
        List<Category> categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category2);
        when(categoryRepository.findAll()).thenReturn(categories);

        // Act
        List<CategoryResponse> response = categoryService.getAllCategories();

        // Assert
        assertEquals(categories.size(), response.size());
        for (int i = 0; i < categories.size(); i++) {
            assertEquals(categories.get(i).getId(), response.get(i).getId());
            assertEquals(categories.get(i).getName(), response.get(i).getName());
        }
    }

    @Test
    public void testGetCategoryById() {
        // Arrange
        String categoryId = "1";
        String categoryName = "Category 1";
        Category category = new Category(categoryId, categoryName);
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        // Act
        CategoryResponse response = categoryService.getCategoryById(categoryId);

        // Assert
        assertNotNull(response);
        assertEquals(categoryId, response.getId());
        assertEquals(categoryName, response.getName());
    }

    // Add more test cases as needed

}
