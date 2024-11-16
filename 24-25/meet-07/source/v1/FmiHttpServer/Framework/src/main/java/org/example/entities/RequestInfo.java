package org.example.entities;

import java.util.HashMap;
import java.util.Objects;

public class RequestInfo {

    private String httpMethod;
    private String httpEndpoint;
    private String httpBody;
    private HashMap<String, String> headers = new HashMap<>();


    public RequestInfo() {
        this.httpMethod = "";
        this.httpEndpoint = "";
    }

    public RequestInfo(String httpMethod, String httpEndpoint) {
        this.httpMethod = httpMethod;
        this.httpEndpoint = httpEndpoint;
    }

    public int getContentLength() {

        String value = this.getHeaderValue("Content-Length");

        if(Objects.isNull(value)) {
            return 0;
        }

        return Integer.parseInt(value);
    }

    public boolean hasContent() {
        return this.getContentLength() > 0;
    }


    public boolean hasMethodAndEndpoint() {

        return !this.getHttpMethod().isEmpty() &&
                !this.getHttpEndpoint().isEmpty();
    }

    public void setHeader(String key, String value) {
        this.headers.put(key, value);
    }

    public boolean hasHeader(String header) {
        return this.headers.containsKey(header);
    }

    public String getHeaderValue(String header) {
        return this.headers.get(header);
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getHttpEndpoint() {
        return httpEndpoint;
    }

    public void setHttpEndpoint(String httpEndpoint) {
        this.httpEndpoint = httpEndpoint;
    }

    public String getHttpBody() {
        return httpBody;
    }

    public void setHttpBody(String httpBody) {
        this.httpBody = httpBody;
    }

    @Override
    public int hashCode() {
        return Objects.hash(httpMethod, httpEndpoint);
    }

    @Override
    public boolean equals(Object obj) {

        if( obj == null || getClass() != obj.getClass()) return false;
        RequestInfo info = (RequestInfo) obj;
        return Objects.equals(httpEndpoint, info.httpEndpoint) &&
                Objects.equals(httpMethod, info.httpMethod);
    }
}