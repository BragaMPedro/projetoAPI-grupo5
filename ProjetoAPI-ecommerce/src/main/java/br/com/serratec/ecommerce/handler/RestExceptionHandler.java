package br.com.serratec.ecommerce.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.serratec.ecommerce.domains.erros.MensagemError;
import br.com.serratec.ecommerce.exceptions.ResourceBadRequestException;
import br.com.serratec.ecommerce.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {
    
    /**
     * @param ex
     * @return
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<MensagemError> handleResourceNotFoundException(ResourceNotFoundException ex){

        MensagemError erro = new MensagemError(404, "Not Found", ex.getMessage());
        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(ResourceBadRequestException.class)
    public ResponseEntity<MensagemError> handleResourceBadRequestException(ResourceBadRequestException ex){

        MensagemError erro = new MensagemError(400, "Bad Request", ex.getMessage());
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensagemError> handleException(Exception ex){

        MensagemError erro = new MensagemError(500, "Internal Server Error", ex.getMessage());
        return new ResponseEntity<>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
