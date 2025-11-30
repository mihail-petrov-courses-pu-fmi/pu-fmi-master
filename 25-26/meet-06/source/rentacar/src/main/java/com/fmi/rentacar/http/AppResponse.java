package com.fmi.rentacar.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

// Абстракция върху ResponseEntity на спринг
// - фактори методи, за по-лесно конструиране на респонс
public class AppResponse {

    private static HashMap<String, Object> responseMap;

    // success
    public static AppResponse success() {

        responseMap = new HashMap<>();
        responseMap.put("status", "success");
        responseMap.put("code"  , HttpStatus.OK.value());

        return new AppResponse();
    }

    // error
    public static AppResponse error() {

        responseMap = new HashMap<>();
        responseMap.put("status", "error");
        responseMap.put("code"  , HttpStatus.BAD_REQUEST.value());
        return new AppResponse();
    }

    public AppResponse withCode(HttpStatus status) {

        responseMap.put("code", status.value());
        return this;
    }

    public AppResponse withMessage(String message) {

        responseMap.put("message", message);
        return  this;
    }

    public AppResponse withData(Object data) {

        responseMap.put("data", data);
        return this;
    }

    public ResponseEntity<Object> send() {

        int code = (int) responseMap.get("code");
        return new ResponseEntity<>(responseMap, HttpStatusCode.valueOf(code));
    }
}
