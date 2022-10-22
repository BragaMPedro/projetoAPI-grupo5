package br.com.serratec.ecommerce.exceptions;

public class ResourceBadRequestException extends RuntimeException{

    public ResourceBadRequestException(String message) {
        super(message);
    }
    
}
