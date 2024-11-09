package org.example.entities;

import java.util.Objects;

public class RequestInfo {

    private String httpMethod;
    private String httpEndpoint;

    public RequestInfo(String httpMethod, String httpEndpoint) {
        this.httpMethod = httpMethod;
        this.httpEndpoint = httpEndpoint;
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