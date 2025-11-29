package com.fmi.solarparkapp.handlers;

import com.fmi.solarparkapp.http.AppResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Ще си направя един метод, който да хваща проблем при валидация
    // на ниво контролер - ПРОИЗВОЛЕН КОНТРОЛЕ. Тази валидация се случва
    // преди да се изпълни кореспондиращия метод на контролера.

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(
            MethodArgumentNotValidException ex
    ) {

        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errorMap.put(error.getField(), error.getDefaultMessage())
        );

        return AppResponse.error()
                .withMessage("Validation failed - when parsing object")
                .withData(errorMap)
                .send();
    }
}
