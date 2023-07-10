package br.com.compassuol.pb.challenge.userservice.ErrorHandling;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
