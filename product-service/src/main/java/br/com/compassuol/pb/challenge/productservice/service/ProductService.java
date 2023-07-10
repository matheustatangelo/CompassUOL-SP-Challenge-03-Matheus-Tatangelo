package br.com.compassuol.pb.challenge.productservice.service;

import br.com.compassuol.pb.challenge.productservice.dto.ProductRequest;
import br.com.compassuol.pb.challenge.productservice.dto.ProductResponse;
import br.com.compassuol.pb.challenge.productservice.model.Category;
import br.com.compassuol.pb.challenge.productservice.model.Product;
import br.com.compassuol.pb.challenge.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.swing.text.html.Option;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;


    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .date(new Date())
                .description(productRequest.getDescription())
                .name(productRequest.getName())
                .imgUrl(productRequest.getImgUrl())
                .price(productRequest.getPrice())
                .categories(productRequest.getCategories())
                .build();

        productRepository.save(product);
        log.info("Product is save: {}", product.getId());

    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .imgUrl(product.getImgUrl())
                .price(product.getPrice())
                .categories(product.getCategories())
                .build();
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }


    public List<ProductResponse> getAllProductsPagination(int pageNumber, int recordCount, String direction, String orderBy) {
        Pageable pageable = PageRequest.of(pageNumber,  recordCount, Sort.Direction.valueOf(direction), orderBy);
        List<Product> products = productRepository.findAll(pageable).toList();

        return products.stream().map(this::mapToProductResponse).toList();
    }

    public void updateProduct(String id, ProductRequest productRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setDate(new Date());
        product.setDescription(productRequest.getDescription());
        product.setName(productRequest.getName());
        product.setImgUrl(productRequest.getImgUrl());
        product.setPrice(productRequest.getPrice());
        product.setCategories(productRequest.getCategories());

        productRepository.save(product);
        log.info("Product is update: {}", product.getId());
    }

    public void deleteProduct(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productRepository.delete(product);
        log.info("Product is delete: {}", product.getId());
    }
}
