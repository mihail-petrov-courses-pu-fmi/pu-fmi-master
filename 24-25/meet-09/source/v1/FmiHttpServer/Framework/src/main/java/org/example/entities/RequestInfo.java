package org.example.entities;

import java.util.HashMap;
import java.util.Objects;

public class RequestInfo {

    private String httpMethod;
    private String httpEndpoint;
    private String httpBody;
    private HashMap<String, String> headers         = new HashMap<>();
    private HashMap<String, String> pathVariables   = new HashMap<>();

    public RequestInfo() {
        this.httpMethod = "";
        this.httpEndpoint = "";
    }

    public RequestInfo(String httpMethod, String httpEndpoint) {
        this.httpMethod = httpMethod;
        this.httpEndpoint = httpEndpoint;
    }

    public boolean isEmpty() {
        return this.httpMethod.isEmpty() ||
                this.httpEndpoint.isEmpty();
    }

    public boolean isProcessable(String method, String endpoint) {

        if(!this.httpMethod.equals(method)) {
            return false;
        }

        return isTemplateEndpointMatchRequestEndpoint(endpoint);
    }

    // /customers/{id}
    // /customers/10
    public boolean isTemplateEndpointMatchRequestEndpoint(String requestEndpoint) {

        String[] templateEndpointPartCollection = this.getHttpEndpoint().split("/");
        String[] requestEndpointPartCollection  = requestEndpoint.split("/");

        // 1. Проверка дали URL адресите имат еднакво количество секции
        if(templateEndpointPartCollection.length != requestEndpointPartCollection.length) {
            return false;
        }

        // 2. Правим проверка секция по секция, като предварително парсваме наличните
        // динамични секции
        for(int i = 0; i < templateEndpointPartCollection.length; i++) {

            if(this.isUrlPartDynamic(templateEndpointPartCollection[i])) {

                String pathVariableName     = this.extractUrlVariable(templateEndpointPartCollection[i]);
                String pathVariableValue    = requestEndpointPartCollection[i];
                this.pathVariables.put(pathVariableName, pathVariableValue);
                continue;
            }

            if(!templateEndpointPartCollection[i].equals(requestEndpointPartCollection[i])) {
                return false;
            }
        }

        return true;
    }

    private boolean isUrlPartDynamic(String templateUrl) {

        return templateUrl.startsWith("{") &&
                templateUrl.endsWith("}");
    }

    private String extractUrlVariable(String template) {
        return template.substring(1, template.length() - 1);
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

    public HashMap<String, String> getPathVariables() {
        return this.pathVariables;
    }

    public void setPathVariables(HashMap<String, String> pathVariables) {
        this.pathVariables = pathVariables;
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