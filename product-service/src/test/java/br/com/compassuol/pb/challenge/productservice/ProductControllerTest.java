package br.com.compassuol.pb.challenge.productservice;

import br.com.compassuol.pb.challenge.productservice.controller.*;
import br.com.compassuol.pb.challenge.productservice.dto.ProductRequest;
import br.com.compassuol.pb.challenge.productservice.dto.ProductResponse;
import br.com.compassuol.pb.challenge.productservice.model.Product;
import br.com.compassuol.pb.challenge.productservice.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class ProductControllerTest {
    private ProductController productController;

    @Mock
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        productController = new ProductController(productService);
    }

    @Test
    public void testCreateProduct() {
        // Arrange
        ProductRequest productRequest = ProductRequest.builder()
                .name("New Product")
                .description("Description")
                .price(BigDecimal.valueOf(10.0))
                .build();
        doNothing().when(productService).createProduct(productRequest);

        // Act
        Mono<ResponseEntity<String>> responseMono = productController.createProduct(productRequest);
        ResponseEntity<String> responseEntity = responseMono.block();

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Produto criado com sucesso!", responseEntity.getBody());
    }

    @Test
    public void testGetAllProducts() {
        // Arrange
        ProductResponse product1 = ProductResponse.builder()
                .id("1")
                .name("Product 1")
                .description("Description 1")
                .price(BigDecimal.valueOf(10.0))
                .build();
        ProductResponse product2 = ProductResponse.builder()
                .id("2")
                .name("Product 2")
                .description("Description 2")
                .price(BigDecimal.valueOf(20.0))
                .build();
        List<ProductResponse> expectedProducts = Arrays.asList(product1, product2);
        when(productService.getAllProducts()).thenReturn(expectedProducts);

        // Act
        List<ProductResponse> actualProducts = productController.getAllProducts();

        // Assert
        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    public void testGetAllProductsPagination() {
        // Arrange
        int pageNumber = 1;
        int recordCount = 10;
        String direction = "asc";
        String orderBy = "name";
        ProductResponse product1 = ProductResponse.builder()
                .id("1")
                .name("Product 1")
                .description("Description 1")
                .price(BigDecimal.valueOf(10.0))
                .build();
        ProductResponse product2 = ProductResponse.builder()
                .id("2")
                .name("Product 2")
                .description("Description 2")
                .price(BigDecimal.valueOf(20.0))
                .build();
        List<ProductResponse> expectedProducts = Arrays.asList(product1, product2);
        when(productService.getAllProductsPagination(pageNumber, recordCount, direction, orderBy)).thenReturn(expectedProducts);

        // Act
        List<ProductResponse> actualProducts = productController.getAllProductsPagination(pageNumber, recordCount, direction, orderBy);

        // Assert
        assertEquals(expectedProducts, actualProducts);
    }

//    @Test
//    public void testGetProductById() {
//        String id = "1";
//        Product product = new Product(id, "Product 1", "Description 1", new BigDecimal("10.00"));
//        when(productService.getProductById(id)).thenReturn(Mono.just(product));
//        ResponseEntity<ProductResponse> expectedResponse = ResponseEntity.ok(new ProductResponse(product));
//        ResponseEntity<ProductResponse> actualResponse = productController.getProductById(id).block();
//        assertEquals(expectedResponse, actualResponse);
//    }

    @Test
    public void testUpdateProduct() {
        // Arrange
        String id = "1";
        ProductRequest productRequest = ProductRequest.builder()
                .name("Updated Product")
                .description("Updated Description")
                .price(BigDecimal.valueOf(20.0))
                .build();
        doNothing().when(productService).updateProduct(id, productRequest);

        // Act
        ResponseEntity<String> response = productController.updateProduct(id, productRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Produto atualizado com sucesso!", response.getBody());
    }

    @Test
    public void testDeleteProduct() {
        // Arrange
        String id = "1";
        doNothing().when(productService).deleteProduct(id);

        // Act
        ResponseEntity<String> response = productController.deleteProduct(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Produto deletado com sucesso!", response.getBody());
    }
}