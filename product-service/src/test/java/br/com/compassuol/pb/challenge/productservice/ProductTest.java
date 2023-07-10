package br.com.compassuol.pb.challenge.productservice;

import br.com.compassuol.pb.challenge.productservice.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;

public class ProductTest {

    @Test
    public void testProduct() {
        // Create a new product
        Product product = Product.builder()
                .name("Test Product")
                .description("This is a test product")
                .price(BigDecimal.valueOf(9.99))
                .imgUrl("https://example.com/test-product.jpg")
                .date(new Date())
                .categories(new HashSet<>())
                .build();

        // Test the product's properties
        Assertions.assertEquals("Test Product", product.getName());
        Assertions.assertEquals("This is a test product", product.getDescription());
        Assertions.assertEquals(BigDecimal.valueOf(9.99), product.getPrice());
        Assertions.assertEquals("https://example.com/test-product.jpg", product.getImgUrl());
        Assertions.assertNotNull(product.getDate());
        Assertions.assertEquals(new HashSet<>(), product.getCategories());
    }
}




