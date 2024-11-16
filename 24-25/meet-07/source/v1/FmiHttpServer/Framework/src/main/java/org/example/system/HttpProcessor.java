package org.example.system;

import org.example.entities.ControllerMeta;
import org.example.entities.RequestInfo;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class HttpProcessor {

    // механизъм за работа с всички мапнати КЛАСОВЕ в приложението
    private ApplicationLoader appLoader = ApplicationLoader.getInstance();

    public String executeController(RequestInfo httpRequest) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        String httpMethod                           = httpRequest.getHttpMethod();
        String httpRequestEndpoint                  = httpRequest.getHttpEndpoint();
        ControllerMeta controllerMethodReference    = null;

        if(controllerMethodReference == null) {
            return "Internal server error";
        }

        Class clazz                 = controllerMethodReference.getClassReference();
        String methodName           = controllerMethodReference.getMethodName();

        var controllerInstance =  clazz.getDeclaredConstructor().newInstance();
        return (String) clazz.getMethod(methodName).invoke(controllerInstance);
    }
}
