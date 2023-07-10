package br.com.compassuol.pb.challenge.productservice;

import br.com.compassuol.pb.challenge.productservice.model.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    @Test
    public void testCategory() {
        // Create a new category
        Category category = Category.builder()
                .id("123")
                .name("Test Category")
                .build();

        // Test the category's properties
        Assertions.assertNotNull(category.getId());
        Assertions.assertEquals("Test Category", category.getName());
    }
}