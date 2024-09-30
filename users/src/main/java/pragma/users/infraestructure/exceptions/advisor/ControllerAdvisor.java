package pragma.users.infraestructure.exceptions.advisor;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pragma.users.infraestructure.exceptions.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {


    @ExceptionHandler(FirstNameValidation.class)
    public ResponseEntity<ExceptionResponse> handleFirstNameValidationException(FirstNameValidation ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(String.format(ex.getMessage()),HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }
    @ExceptionHandler(LastNameValidation.class)
    public ResponseEntity<ExceptionResponse> handleLastNameValidationException(LastNameValidation ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(String.format(ex.getMessage()),HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }
    @ExceptionHandler(PasswordValidation.class)
    public ResponseEntity<ExceptionResponse> handlePasswordValidationException(PasswordValidation ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(String.format(ex.getMessage()),HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(AgeValidationException.class)
    public ResponseEntity<ExceptionResponse> handleAgeValidationException(AgeValidationException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(String.format(ex.getMessage()),HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(PhoneNumberValidation.class)
    public ResponseEntity<ExceptionResponse> handlePhoneValidationException(PhoneNumberValidation ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(String.format(ex.getMessage()),HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(EmailValidationException.class)
    public ResponseEntity<ExceptionResponse> handleEmailValidationException(EmailValidationException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(String.format(ex.getMessage()),HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(IdentityDocumentValidation.class)
    public ResponseEntity<ExceptionResponse> handleIdentityDocumentValidationException(IdentityDocumentValidation ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(String.format(ex.getMessage()),HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,Object> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->

                errors.put(error.getField(),error.getDefaultMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }



}
