package com.example.car_rental_service.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.lang.model.UnknownEntityException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class BaseExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        var errors=methodArgumentNotValidException.getAllErrors();

        ErrorMessage message=null;

        if(errors!=null && !errors.isEmpty()){
            message=new ErrorMessage(errors.get(0).getDefaultMessage(), 400);
            return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
        }

        message=new ErrorMessage("Bad request", 400);
        return new ResponseEntity<ErrorMessage>(message,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NoSuchElementException.class, NumberFormatException.class})
    public ResponseEntity<ErrorMessage> handleNotFoundException(Exception e){
        ErrorMessage message=new ErrorMessage("Not found", 404);
        return new ResponseEntity<ErrorMessage>(message,HttpStatus.NOT_FOUND);
    }

     @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> handleIllegalException(IllegalArgumentException e){
        ErrorMessage message=new ErrorMessage(e.getMessage(), 400);
        return new ResponseEntity<ErrorMessage>(message,HttpStatus.BAD_REQUEST);
    }

     @ExceptionHandler(UnknownEntityException.class)
    public ResponseEntity<ErrorMessage> handleNotReadableException(UnknownEntityException e){
        ErrorMessage message=new ErrorMessage("Bad Request", 400);
        return new ResponseEntity<ErrorMessage>(message,HttpStatus.BAD_REQUEST);
    }

}
