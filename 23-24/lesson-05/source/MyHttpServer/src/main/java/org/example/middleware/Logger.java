package org.example.middleware;

import org.example.server.contracts.HttpRequestObject;
import org.example.server.contracts.HttpResponseObject;
import org.example.server.contracts.HttpStatusCodeEnum;
import org.example.server.contracts.MiddlewareContainer;
import org.example.tags.HttpMiddleware;
import org.example.tags.HttpMiddlewareMethod;

@HttpMiddleware
public class Logger {

    @HttpMiddlewareMethod
    public void applyLogs(HttpRequestObject requestObject, HttpResponseObject responseObject, MiddlewareContainer next ) {
        System.out.println("logger applied");
        next.applyNext(requestObject, responseObject);

    }

    @HttpMiddlewareMethod
    public void Forbidden(HttpRequestObject requestObject, HttpResponseObject responseObject, MiddlewareContainer next ) {
        System.out.println("forbidden");
        responseObject.setStatusCode(HttpStatusCodeEnum.FORBIDDEN);
//        next.applyNext(requestObject, responseObject);

    }

    @HttpMiddlewareMethod
    public void applyLogs2(HttpRequestObject requestObject, HttpResponseObject responseObject, MiddlewareContainer next ) {
        System.out.println("logger 2 applied");
        next.applyNext(requestObject, responseObject);
    }
}
