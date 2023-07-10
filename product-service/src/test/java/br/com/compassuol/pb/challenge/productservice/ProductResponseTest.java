package br.com.compassuol.pb.challenge.productservice;

import br.com.compassuol.pb.challenge.productservice.dto.ProductResponse;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductResponseTest {
    @Test
    public void testProductResponse() {
        // Arrange
        String productId = "1";
        Date productDate = new Date();
        String productDescription = "This is a test product.";
        String productName = "Test Product";
        String productImgUrl = "http://test.com/image.jpg";
        BigDecimal productPrice = new BigDecimal("9.99");
        ProductResponse productResponse = ProductResponse.builder()
                .id(productId)
                .date(productDate)
                .description(productDescription)
                .name(productName)
                .imgUrl(productImgUrl)
                .price(productPrice)
                .build();

        // Act
        String resultId = productResponse.getId();
        Date resultDate = productResponse.getDate();
        String resultDescription = productResponse.getDescription();
        String resultName = productResponse.getName();
        String resultImgUrl = productResponse.getImgUrl();
        BigDecimal resultPrice = productResponse.getPrice();

        // Assert
        assertEquals(productId, resultId);
        assertEquals(productDate, resultDate);
        assertEquals(productDescription, resultDescription);
        assertEquals(productName, resultName);
        assertEquals(productImgUrl, resultImgUrl);
        assertEquals(productPrice, resultPrice);
    }
}
