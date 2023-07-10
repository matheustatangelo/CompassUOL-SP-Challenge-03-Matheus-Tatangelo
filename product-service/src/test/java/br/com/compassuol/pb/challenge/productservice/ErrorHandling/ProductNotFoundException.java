package br.com.compassuol.pb.challenge.productservice.ErrorHandling;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
