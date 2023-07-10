package br.com.compassuol.pb.challenge.productservice;

import br.com.compassuol.pb.challenge.productservice.dto.CategoryResponse;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryResponseTest {
    @Test
    public void testCategoryResponse() {
        // Arrange
        String categoryId = "1";
        String categoryName = "Test Category";
        CategoryResponse categoryResponse = new CategoryResponse(categoryId, categoryName);

        // Act
        String resultId = categoryResponse.getId();
        String resultName = categoryResponse.getName();

        // Assert
        assertEquals(categoryId, resultId);
        assertEquals(categoryName, resultName);
    }
}
