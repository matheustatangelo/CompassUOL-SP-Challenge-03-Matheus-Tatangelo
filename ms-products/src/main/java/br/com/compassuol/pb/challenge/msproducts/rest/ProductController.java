package br.com.compassuol.pb.challenge.msproducts.rest;

import br.com.compassuol.pb.challenge.msproducts.dto.ProductRequest;
import br.com.compassuol.pb.challenge.msproducts.dto.ProductResponse;
import br.com.compassuol.pb.challenge.msproducts.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
       return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public String buscar() {
        return "buscar produto por id";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }

    @PutMapping("/{id}")
    public String atualizar() {
        return "atualizar produto";
    }

    @DeleteMapping("/{id}")
    public String deletar() {
        return "deletar produto";
    }

}