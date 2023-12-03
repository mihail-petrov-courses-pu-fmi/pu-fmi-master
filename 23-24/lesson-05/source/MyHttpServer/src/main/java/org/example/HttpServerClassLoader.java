package org.example;

import org.example.server.contracts.HttpRequestObject;
import org.example.tags.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class HttpServerClassLoader {

    public static void bootstrapApplication() throws IOException, ClassNotFoundException {

        // Сканиране на директория - съдържаща контролери
        BufferedReader appLoaderStreamReader = scanDirectory("org/example/app");

        // Зареждане на фактическия клас
        String line = "";
        while((line = appLoaderStreamReader.readLine()) != null) {

            String classPath    = "org.example.app." + line.replace(".class", "");
            Class classInstance =  Class.forName(classPath);

            // Работим само с класове, които са анотирани като Controller - и
            boolean isClassController = classInstance.isAnnotationPresent(
                    HttpController.class
            );

            if(!isClassController) continue;

            Method[] instanceMethodCollection = classInstance.getMethods();

            for(Method instanceMethod : instanceMethodCollection) {

                int modificatorCode     =  instanceMethod.getModifiers();
                boolean isPublic        = Modifier.isPublic(modificatorCode);
                boolean hasAnnotation   = instanceMethod.isAnnotationPresent(
                        org.example.tags.HttpMethod.class
                );
                boolean isMethodProcessable = isPublic && hasAnnotation;

                if(!isMethodProcessable) continue;

                HttpMethod httpMethodAnnotation = instanceMethod.getAnnotation(
                        org.example.tags.HttpMethod.class
                );

                String httpMethod           = httpMethodAnnotation.method();
                String httpPath             = httpMethodAnnotation.path();
                HttpRequestObject response = new HttpRequestObject(httpMethod, httpPath);
                HttpTable.addRequest(response, classInstance, instanceMethod);
            }
        }
    }

    public static void bootstrapServices() throws IOException, ClassNotFoundException {

        // Сканиране на директория - съдържаща сервиси
        BufferedReader appLoaderStreamReader = scanDirectory("org/example/services");

        // Зареждане на фактическия клас
        String line = "";
        while((line = appLoaderStreamReader.readLine()) != null) {

            String classPath    = "org.example.services." + line.replace(".class", "");
            Class classInstance =  Class.forName(classPath);

            // Работим само с класове, които са анотирани като Controller - и
            boolean isClassService = classInstance.isAnnotationPresent(
                    Service.class
            );

            if(!isClassService) continue;

            try {
                HttpTable.addService(classInstance.getName(), classInstance.getDeclaredConstructor().newInstance());
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
    }


    public static void bootstrapMiddlewareClasses() throws IOException, ClassNotFoundException {

        BufferedReader bufferedReader = scanDirectory("org/example/middleware");

        // Зареждане на фактическия клас
        String line = "";
        while((line = bufferedReader.readLine()) != null) {

            String classPath    = "org.example.middleware." + line.replace(".class", "");
            Class classInstance =  Class.forName(classPath);

            boolean isClassMiddleware = classInstance.isAnnotationPresent(
                    HttpMiddleware.class
            );

            if(!isClassMiddleware) continue;

            Method[] instanceMethodCollection = classInstance.getMethods();

            for(Method instanceMethod : instanceMethodCollection) {

                int modificatorCode     =  instanceMethod.getModifiers();
                boolean isPublic        = Modifier.isPublic(modificatorCode);
                boolean hasAnnotation   = instanceMethod.isAnnotationPresent(
                        org.example.tags.HttpMiddlewareMethod.class
                );
                boolean isMethodProcessable = isPublic && hasAnnotation;

                if(!isMethodProcessable) continue;
                HttpTable.addMiddleware(classInstance, instanceMethod);
            }
        }
    }

    private static BufferedReader scanDirectory(String name) {
        ClassLoader appLoader = ClassLoader.getSystemClassLoader();
        InputStream appLoaderStream = appLoader.getResourceAsStream(name);
        return new BufferedReader(new InputStreamReader(appLoaderStream));
    }
}
