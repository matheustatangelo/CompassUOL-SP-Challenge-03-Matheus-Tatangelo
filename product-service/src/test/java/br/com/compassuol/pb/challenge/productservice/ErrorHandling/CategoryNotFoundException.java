package br.com.compassuol.pb.challenge.productservice.ErrorHandling;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
