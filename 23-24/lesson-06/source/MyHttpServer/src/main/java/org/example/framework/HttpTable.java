package org.example.framework;

import org.example.framework.container.ClassMethodComposition;
import org.example.framework.server.contracts.HttpRequestObject;
import org.example.framework.server.contracts.HttpResponseObject;
import org.example.framework.server.contracts.HttpStatusCodeEnum;
import org.example.framework.server.parsers.MethodReturnTypeParser;
import org.example.framework.tags.HttpParameterHash;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class HttpTable {

    public static String processRequest(
            HttpRequestObject request) {

        String tableKey                         = getRequestKey(request);
        ClassMethodComposition classComposition = DependencyInjectionContainer.getComponent(tableKey);

        HttpResponseObject httpResponse = new HttpResponseObject();

        if(classComposition == null) {

            httpResponse.setStatusCode(HttpStatusCodeEnum.NOT_FOUND);
            httpResponse.setStatusMessage("Error");
            httpResponse.setResponseBody("<h1>Page Not found</h1>");
            return httpResponse.getHttpMessage();
        }

        var resultObject        = executeControllerMethod(classComposition, request, httpResponse);
        String httpStringResult = MethodReturnTypeParser.parse(resultObject, request, httpResponse);

        httpResponse.setStatusCode(HttpStatusCodeEnum.OK);
        httpResponse.setStatusMessage("Success");
        httpResponse.setResponseBody(httpStringResult);
        return httpResponse.getHttpMessage();
    }

    private static Object executeControllerMethod(
            ClassMethodComposition classComposition,
            HttpRequestObject request,
            HttpResponseObject response
    ) {

        try {

            // Взимам конструкторите на контролера
            var constructorControllerCollection = classComposition.classReference.getConstructors();
            ArrayList<Object> serviceInstanceCollection = new ArrayList<>();
            Class[] serviceClassCollection = null;

            for(Constructor element : constructorControllerCollection) {
                var constructorParameterTypeCollection  = element.getGenericParameterTypes();
                serviceClassCollection                  = element.getParameterTypes();

                for(Type parameterType : constructorParameterTypeCollection) {

                    String parameterName    = parameterType.getTypeName();
                    ClassMethodComposition serviceReference  = DependencyInjectionContainer.getComponent(parameterName);
                    serviceInstanceCollection.add(serviceReference.getInstance());
                }
            }

            // ИНстанция на контролер
            var instance = classComposition.classReference.getDeclaredConstructor(
                    serviceClassCollection
            ).newInstance(
                    serviceInstanceCollection.toArray()
            );

            Annotation[][] parameterAnnotationCollection =
                    classComposition.methodReference.getParameterAnnotations();

            // Ако имаш една единствена анотация, която да кореспондира с параметъра
            if(parameterAnnotationCollection.length == 1) {
                if(parameterAnnotationCollection[0].length == 1) {
                    if(parameterAnnotationCollection[0][0] instanceof HttpParameterHash) {

                        return classComposition.methodReference.invoke(
                                instance,
                                request.getHttpQueryParameterHash()
                        );
                    }
                }
            }

            return classComposition.methodReference.invoke(instance);

        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getRequestKey(HttpRequestObject request) {
        return  request.getHttpMethod() + ":" + request.getHttpPath();
    }
}
