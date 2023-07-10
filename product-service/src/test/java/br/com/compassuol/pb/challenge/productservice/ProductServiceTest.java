package br.com.compassuol.pb.challenge.productservice;

import br.com.compassuol.pb.challenge.productservice.dto.ProductResponse;
import br.com.compassuol.pb.challenge.productservice.model.Product;
import br.com.compassuol.pb.challenge.productservice.repository.ProductRepository;
import br.com.compassuol.pb.challenge.productservice.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testGetAllProducts() {
        // Arrange
        List<Product> products = new ArrayList<>();
        products.add(new Product("1", new Date(), "Product 1", "Description 1", "http://test.com/image1.jpg", new BigDecimal("9.99"), new HashSet<>()));
        products.add(new Product("2", new Date(), "Product 2", "Description 2", "http://test.com/image2.jpg", new BigDecimal("19.99"), new HashSet<>()));
        products.add(new Product("3", new Date(), "Product 3", "Description 3", "http://test.com/image3.jpg", new BigDecimal("29.99"), new HashSet<>()));
        when(productRepository.findAll()).thenReturn(products);

        // Act
        List<ProductResponse> result = productService.getAllProducts();

        // Assert
        assertEquals(products.size(), result.size());
        for (int i = 0; i < products.size(); i++) {
            assertEquals(products.get(i).getId(), result.get(i).getId());
            assertEquals(products.get(i).getDate(), result.get(i).getDate());
            assertEquals(products.get(i).getDescription(), result.get(i).getDescription());
            assertEquals(products.get(i).getName(), result.get(i).getName());
            assertEquals(products.get(i).getImgUrl(), result.get(i).getImgUrl());
            assertEquals(products.get(i).getPrice(), result.get(i).getPrice());
            assertEquals(products.get(i).getCategories(), result.get(i).getCategories());
        }
    }

    @Test
    public void testGetProductById() {
        // Arrange
        String productId = "1";
        Product product = new Product(productId, new Date(), "Test Product", "This is a test product.", "http://test.com/image.jpg", new BigDecimal("9.99"), new HashSet<>());
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Act
        ProductResponse result = productService.getProductById(productId);

        // Assert
        assertEquals(product.getId(), result.getId());
        assertEquals(product.getDate(), result.getDate());
        assertEquals(product.getDescription(), result.getDescription());
        assertEquals(product.getName(), result.getName());
        assertEquals(product.getImgUrl(), result.getImgUrl());
        assertEquals(product.getPrice(), result.getPrice());
        assertEquals(product.getCategories(), result.getCategories());
    }
}
