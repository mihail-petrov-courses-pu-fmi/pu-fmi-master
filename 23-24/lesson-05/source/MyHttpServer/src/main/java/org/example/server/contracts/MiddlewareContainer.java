package org.example.server.contracts;

import org.example.ClassMethodComposition;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class MiddlewareContainer {

    private List<ClassMethodComposition> middlewares = new ArrayList<>();
    private int currentMiddleware = 0;


    public void addMiddleware(ClassMethodComposition classMethodComposition) {
        middlewares.add(classMethodComposition);
    }

    public boolean start(HttpRequestObject request, HttpResponseObject response) {
        currentMiddleware = 0;
        executeMiddlewareMethod(middlewares.get(currentMiddleware), request, response);

        return allMiddlewaresApplied();
    }

    public void applyNext(HttpRequestObject request, HttpResponseObject response) {
        if(allMiddlewaresApplied()) {
            return;
        }

        currentMiddleware++;
        executeMiddlewareMethod(middlewares.get(currentMiddleware), request, response);
    }

    private void executeMiddlewareMethod(ClassMethodComposition middleware, HttpRequestObject request, HttpResponseObject response) {
        try {
            // Инстанция на middleware
            var instance = middleware.getClassReference().getDeclaredConstructor().newInstance();
            middleware.getMethodReference().invoke(
                    instance,
                    request,
                    response,
                    this
            );
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean allMiddlewaresApplied() {
        return currentMiddleware == middlewares.size();
    }
}
