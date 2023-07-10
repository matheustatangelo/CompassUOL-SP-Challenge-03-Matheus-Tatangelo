package br.com.compassuol.pb.challenge.productservice;

import br.com.compassuol.pb.challenge.productservice.dto.ProductRequest;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductRequestTest {
    @Test
    public void testProductRequest() {
        // Arrange
        String productName = "Test Product";
        String productDescription = "This is a test product.";
        BigDecimal productPrice = new BigDecimal("9.99");
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName(productName);
        productRequest.setDescription(productDescription);
        productRequest.setPrice(productPrice);

        // Act
        String resultName = productRequest.getName();
        String resultDescription = productRequest.getDescription();
        BigDecimal resultPrice = productRequest.getPrice();

        // Assert
        assertEquals(productName, resultName);
        assertEquals(productDescription, resultDescription);
        assertEquals(productPrice, resultPrice);
    }
}
