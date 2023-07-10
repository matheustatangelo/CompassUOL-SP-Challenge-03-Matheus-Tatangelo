package br.com.compassuol.pb.challenge.productservice;

import br.com.compassuol.pb.challenge.productservice.dto.CategoryRequest;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryRequestTest {
    @Test
    public void testCategoryRequest() {
        // Arrange
        String categoryName = "Test Category";
        CategoryRequest categoryRequest = new CategoryRequest(categoryName);

        // Act
        String result = categoryRequest.getName();

        // Assert
        assertEquals(categoryName, result);
    }
}
