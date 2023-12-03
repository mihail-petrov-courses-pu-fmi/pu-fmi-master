package org.example.framework;

import org.example.framework.container.ClassMethodComposition;
import org.example.framework.server.contracts.HttpRequestObject;
import org.example.framework.tags.HttpController;
import org.example.framework.tags.HttpMethod;
import org.example.framework.tags.Service;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;

public class DependencyInjectionContainer {

    private static HashMap<String, ClassMethodComposition> controllerTable = new HashMap<>();
    private static HashMap<String, ClassMethodComposition> serviceTable = new HashMap<>();

    public static void addComponent(Class classReference) {

        if(classReference.isAnnotationPresent(HttpController.class)) {
            processController(classReference);
        }

        if(classReference.isAnnotationPresent(Service.class)) {
            processService(classReference);
        }
    }

    public static ClassMethodComposition getComponent(String componentId) {

        if(controllerTable.containsKey(componentId)) {
            return controllerTable.get(componentId);
        }

        if(serviceTable.containsKey(componentId)) {
            return serviceTable.get(componentId);
        }

        return null;
    }

    private static void processController(Class classReference) {

        Method[] instanceMethodCollection = classReference.getMethods();

        for(Method instanceMethod : instanceMethodCollection) {

            int modificatorCode     =  instanceMethod.getModifiers();
            boolean isPublic        = Modifier.isPublic(modificatorCode);
            boolean hasAnnotation   = instanceMethod.isAnnotationPresent(
                    HttpMethod.class
            );

            boolean isMethodProcessable = isPublic && hasAnnotation;
            if(!isMethodProcessable) continue;

            HttpMethod httpMethodAnnotation = instanceMethod.getAnnotation(
                    HttpMethod.class
            );

            String httpMethod           = httpMethodAnnotation.method();
            String httpPath             = httpMethodAnnotation.path();
            String dependencyId         = httpMethod + ":" + httpPath;

            controllerTable.put(dependencyId, new ClassMethodComposition(classReference, instanceMethod));
        }
    }


    private static void processService(Class classReference) {
        serviceTable.put(classReference.getName(), new ClassMethodComposition(classReference));
    }
}
