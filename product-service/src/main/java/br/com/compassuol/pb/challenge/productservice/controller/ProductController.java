package br.com.compassuol.pb.challenge.productservice.controller;

import br.com.compassuol.pb.challenge.productservice.dto.ProductRequest;
import br.com.compassuol.pb.challenge.productservice.dto.ProductResponse;
import br.com.compassuol.pb.challenge.productservice.model.Product;
import br.com.compassuol.pb.challenge.productservice.repository.ProductRepository;
import br.com.compassuol.pb.challenge.productservice.service.ProductService;
import com.netflix.appinfo.InstanceInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.core.publisher.Mono;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private RestTemplate restTemplate;

    private final ProductService productService;


    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<String>> createProduct(@RequestBody ProductRequest productRequest) {
        return Mono.fromCallable(() -> {
            productService.createProduct(productRequest);
            return new ResponseEntity<>("Produto criado com sucesso!", HttpStatus.CREATED);
        }).subscribeOn(Schedulers.boundedElastic());
    }
    // METHOD: POST PATH: /products

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<String> createProduct(@RequestBody ProductRequest productRequest){
//        productService.createProduct(productRequest);
//        return new ResponseEntity<>("Produto criado com sucesso!", HttpStatus.CREATED);
//    }

    //Código que estava pegando, fiz o de cima para fins de estudo/testes
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public String createProduct(@RequestBody ProductRequest productRequest){
//        productService.createProduct(productRequest);
//        return "Produto criado com sucesso!";
//    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }

    // METHOD: GET PATH: /products
    @GetMapping("/{pageNumber}/{recordCount}/{direction}/{orderBy}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProductsPagination(@PathVariable int pageNumber, @PathVariable int recordCount, @PathVariable String direction, @PathVariable String orderBy){
        return productService.getAllProductsPagination(pageNumber, recordCount, direction, orderBy);
    }


    // METHOD: GET PATH: /products/{id}

    //tá pegando
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable String id){
        return productService.getProductById(id)
                .map(product -> ResponseEntity.ok(new ProductResponse(product)))
                .orElse(ResponseEntity.notFound().build());
    }


    // METHOD: PUT PATH: /products/{id} Teste com ResponseEntity para fins de estudo
//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public String updateProduct(@PathVariable String id, @RequestBody ProductRequest productRequest){
//       productService.updateProduct(id, productRequest);
//         return "Produto atualizado com sucesso!";
//    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity <String> updateProduct(@PathVariable String id, @RequestBody ProductRequest productRequest){
        productService.updateProduct(id, productRequest);
    return new ResponseEntity<>("Produto atualizado com sucesso!", HttpStatus.OK);
    }

    // METHOD: DELETE PATH: /products/{id}
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public String deleteProduct(@PathVariable String id){
//        productService.deleteProduct(id);
//        return "Produto deletedo com sucesso!";
//    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity <String> deleteProduct(@PathVariable String id){
        productService.deleteProduct(id);
        return new ResponseEntity<>("Produto deletado com sucesso!", HttpStatus.OK);
    }


}

