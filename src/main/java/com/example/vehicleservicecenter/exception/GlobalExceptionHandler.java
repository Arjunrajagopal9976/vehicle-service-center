package com.example.vehicleservicecenter.exception;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import java.time.LocalDateTime;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
 public record ErrorBody(LocalDateTime timestamp,int status,String error,String message,Map<String,String> validationErrors){}
 @ExceptionHandler(NotFoundException.class) ResponseEntity<ErrorBody> notFound(NotFoundException e){return build(HttpStatus.NOT_FOUND,e.getMessage(),Map.of());}
 @ExceptionHandler(BadRequestException.class) ResponseEntity<ErrorBody> badRequest(BadRequestException e){return build(HttpStatus.BAD_REQUEST,e.getMessage(),Map.of());}
 @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<ErrorBody> validation(MethodArgumentNotValidException e){
  Map<String,String> errors=new LinkedHashMap<>(); e.getBindingResult().getFieldErrors().forEach(x->errors.put(x.getField(),x.getDefaultMessage()));
  return build(HttpStatus.BAD_REQUEST,"Validation failed",errors);
 }
 @ExceptionHandler(HttpMessageNotReadableException.class) ResponseEntity<ErrorBody> invalidBody(HttpMessageNotReadableException e){return build(HttpStatus.BAD_REQUEST,"Invalid request body or enum value",Map.of());}
 private ResponseEntity<ErrorBody> build(HttpStatus s,String m,Map<String,String> f){return ResponseEntity.status(s).body(new ErrorBody(LocalDateTime.now(),s.value(),s.getReasonPhrase(),m,f));}
}
