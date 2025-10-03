package com.pm.ecommerce.exception;

import com.pm.ecommerce.common.dto.ResultDTO;
import com.pm.ecommerce.common.utils.ResultBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@Slf4j
@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        HashMap<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error ->
                errors.put(error.getObjectName(), error.getDefaultMessage())
        );
        return ResultBuilder.error(errors.toString());
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResultDTO handleCategoryNotFoundException(CategoryNotFoundException ex) {
        log.error("exception is :: "+ex.getMessage());
        return ResultBuilder.error(ex.getMessage());
    }


}
