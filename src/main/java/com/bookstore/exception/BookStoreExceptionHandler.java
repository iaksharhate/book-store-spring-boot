package com.bookstore.exception;

import com.bookstore.dto.MasterResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class BookStoreExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MasterResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception){
        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
        List<String> errormessage = errorList.stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());
        MasterResponse masterResponse =
                new MasterResponse("F", "500", errormessage);
        return new ResponseEntity<>(masterResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<MasterResponse> handleMethodArgumentNotValidException(
            CustomException exception){
        MasterResponse masterResponse =
                new MasterResponse("F", "500", exception.getMessage());
        return new ResponseEntity<>(masterResponse, HttpStatus.BAD_REQUEST);
    }
}
