package org.example.system;

public class ResponseMessage {

    private int code;
    private String message;

    public ResponseMessage(String message) {
        this.message    = message;
        this.code       = 200;
    }

    public ResponseMessage(String message, int statusCode) {
        this.message    = message;
        this.code       = statusCode;
    }

    public int getCode() {
        return code;
    }

    public String getCodeMessage() {

        if(code == 200) return "OK";
        if(code == 404) return "Page Not Found";
        if(code == 500) return "Internal Server Error";

        return ":(";
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
