package com.project02_solar_park_api.solarparkapi.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class AppResponse {

    private static HashMap<String, Object> response;

    public static AppResponse success() {

        response = new HashMap<>();
        response.put("status", "success");
        response.put("code", HttpStatus.OK.value());
        return new AppResponse();
    }

    public static AppResponse error() {

        response = new HashMap<>();
        response.put("status", "error");
        response.put("code", HttpStatus.BAD_REQUEST.value());
        return new AppResponse();
    }


    // метод за статус код
    public AppResponse withCode(HttpStatus code) {
        response.put("code", code.value());
        return this;
    }

    // метод за съобщения
    public AppResponse withMessage(String message) {
        response.put("message", message);
        return this;
    }

    public AppResponse withData(Object data) {
        response.put("data", data);
        return this;
    }

    public ResponseEntity<Object> build() {

        int code = (int) response.get("code");
        return new ResponseEntity<Object>(response, HttpStatusCode.valueOf(code));
    }
}
