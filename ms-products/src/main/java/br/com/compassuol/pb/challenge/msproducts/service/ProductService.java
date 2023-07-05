package br.com.compassuol.pb.challenge.msproducts.service;

import br.com.compassuol.pb.challenge.msproducts.dto.ProductRequest;
import br.com.compassuol.pb.challenge.msproducts.dto.ProductResponse;
import br.com.compassuol.pb.challenge.msproducts.entities.Category;
import br.com.compassuol.pb.challenge.msproducts.entities.Product;
import br.com.compassuol.pb.challenge.msproducts.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        // enviar mensagem para o rabbitmq

        Product product = Product.builder()
                .date(productRequest.getDate())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .imgUrl(productRequest.getImgUrl())
                .price(productRequest.getPrice())
                .categories(productRequest.getCategories())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .date(product.getDate())
                .name(product.getName())
                .description(product.getDescription())
                .imgUrl(product.getImgUrl())
                .price(product.getPrice())
                .categories(product.getCategories())
                .build();
    }


    // listar
    // buscar
    // cadastrar
    // atualizar
    // deletar


}
