package br.com.serratec.ecommerce.handler;

import java.util.NoSuchElementException;

import javax.validation.ValidationException;

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
    
    //Exceptions Genéricas ("Exception.class")
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensagemError> handleException(Exception ex){

        MensagemError erro = new MensagemError(500, "Internal Server Error", ex.getMessage());
        return new ResponseEntity<>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<MensagemError> handleValidationException(ValidationException ex){

        MensagemError erro = new MensagemError(400, "Opa, houve um erro de validação. Cheque os campos da sua requisição.", ex.getMessage());
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<MensagemError> handleIllegalAccessException(IllegalAccessException ex){

        //VER FIELD ERRORS

        MensagemError erro = new MensagemError(400, "Validation Error.", ex.getMessage());
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<MensagemError> handleNoSuchElementException(NoSuchElementException ex){

        MensagemError erro = new MensagemError(404, "Lamento, o objeto procurado nâo existe", ex.getMessage());
        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }
}
