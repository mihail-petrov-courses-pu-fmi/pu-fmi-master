package org.example.system;

import org.example.entities.ControllerMeta;
import org.example.entities.RequestInfo;
import org.example.steriotypes.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class HttpProcessor {

    // механизъм за работа с всички мапнати КЛАСОВЕ в приложението
    private ApplicationLoader appLoader = ApplicationLoader.getInstance();

    public String executeController(RequestInfo httpRequest) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        String httpMethod                           = httpRequest.getHttpMethod();
        String httpRequestEndpoint                  = httpRequest.getHttpEndpoint();
        ControllerMeta controllerMethodReference    = null;

        // 1. Преглеждам всички контролери и правя проверка дали END POINT темплейта съвпада с ENDPOIINT URL
        for(RequestInfo controllerRequestInfo : this.appLoader.getControllerTable().keySet()) {

            if(controllerRequestInfo.isProcessable(httpMethod, httpRequestEndpoint)) {

                controllerMethodReference = this.appLoader.getController(controllerRequestInfo);
                httpRequest.setPathVariables(controllerRequestInfo.getPathVariables());
                break;
            }
        }

        if(controllerMethodReference == null) {
            return "Internal server error";
        }

        Class clazz                 = controllerMethodReference.getClassReference();
        String methodName           = controllerMethodReference.getMethodName();
        Class<?>[] methodSignature  = this.buildMethodParameterTypes(controllerMethodReference);
        Object[] arguments          = this.buildMethodArguments(controllerMethodReference, httpRequest);

        var controllerInstance =  clazz.getDeclaredConstructor().newInstance();

        // За да закача външна функционалност (DI) трябва да обходя всички полета и да проверя дали те имат
        // анотация @Autowired
        Field[] fieldCollection     = clazz.getDeclaredFields();
        for(Field field : fieldCollection) {
            if(field.isAnnotationPresent(org.example.steriotypes.Autowired.class)) {

                Autowired autowiredAnnotation   = field.getAnnotation(Autowired.class);
                boolean isSingleton             = autowiredAnnotation.isSingleton();

                // Взимаме типът на полето и го търсим в таблицата с вече откритите DI материали aka Injectables
                field.setAccessible(true);
                Class injectableMaterial    = field.getType();
                var injectableInstance      = this.appLoader.getInjectable(injectableMaterial, isSingleton);

                field.set(controllerInstance, injectableInstance);
            }
        }

        return (String) clazz.getMethod(methodName, methodSignature).invoke(controllerInstance, arguments);
    }

    private Class<?>[] buildMethodParameterTypes(ControllerMeta controllerMeta) {

        // 1. Трябва да си взема структурата на параметрите
        HashMap<Integer, String> pathVariableIndex  = controllerMeta.getPathVariableIndex();
        Class<?>[] parameterTypes                   = new Class<?>[pathVariableIndex.size()];

        for(int index: pathVariableIndex.keySet()) {
            parameterTypes[index] = int.class;
        }

        return parameterTypes;
    }
    
    private Object[] buildMethodArguments(ControllerMeta controllerMeta, RequestInfo requestInfo) {

        HashMap<Integer, String> pathVariableIndex  = controllerMeta.getPathVariableIndex();
        HashMap<String, String> pathVariables       = requestInfo.getPathVariables();
        Object[] arguments                          = new Object[pathVariableIndex.size()];

        for(int index: pathVariableIndex.keySet()) {
            String variableName     = pathVariableIndex.get(index);
            String variableValue    = pathVariables.get(variableName);

            if(variableValue != null) {
                arguments[index] = Integer.parseInt(variableValue);
            }
        }

        return arguments;
    }
}
