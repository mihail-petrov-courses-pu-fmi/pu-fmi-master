package org.example.system;

import org.example.entities.ControllerMeta;
import org.example.steriotypes.*;

import org.example.entities.RequestInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class ApplicationLoader {

    private static ApplicationLoader instance = null;

    private ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    private HashMap<RequestInfo, ControllerMeta> controllerLookupTable = new HashMap<>();

    // гаранция че няма да се създаде нова инстанция
    private ApplicationLoader() {

    }

    public static ApplicationLoader getInstance() {

        // имаме ли вече инстанция
        if(instance == null) {
            instance = new ApplicationLoader();
        }

        return instance;
    }


    public String executeController(RequestInfo httpRequest) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        String httpMethod   = httpRequest.getHttpMethod();
        String httpEndpoint = httpRequest.getHttpEndpoint();

        ControllerMeta controllerMethodReference = this.controllerLookupTable.get(new RequestInfo(httpMethod, httpEndpoint));

        if(controllerMethodReference == null) {
            return "";
        }

        Class clazz         = controllerMethodReference.getClassReference();
        String methodName   = controllerMethodReference.getMethodName();

        var controllerInstance =  clazz.getDeclaredConstructor().newInstance();
        return (String) clazz.getMethod(methodName).invoke(controllerInstance);
    }

   public void findAllClasses(String packageName) throws IOException, ClassNotFoundException {

        InputStream classLoaderStream = this.classLoader.getResourceAsStream(packageName.replace(".", "/"));
        BufferedReader classReader    = new BufferedReader(new InputStreamReader(classLoaderStream));

        String packageReference = "";
        while((packageReference = classReader.readLine()) != null) {

            if(!packageReference.contains(".class")) {
                findAllClasses(packageName + "." + packageReference);
                continue;
            }

            if(packageReference.contains(".class")) {
                this.classParser(packageReference, packageName);
            }
        }
   }

   private void classParser(String packageReference, String packageName) throws ClassNotFoundException {

       String className        = packageReference.replace(".class", "");
       String fullClassName    = packageName + "." + className;
       Class clazz             =  Class.forName(fullClassName);

       if(clazz.isAnnotationPresent(org.example.steriotypes.Controller.class)) {
        this.parseController(clazz);
       }
   }

   private void parseController(Class clazz) {

        // 1. Да вземем всички методи на този контролер, НО САМО ако са публични
       Method[] controllerClassMethodCollection =  clazz.getMethods();

       for(Method method : controllerClassMethodCollection) {

           if(method.isAnnotationPresent(org.example.steriotypes.GetMapping.class)) {
               GetMapping annotation    = method.getAnnotation(org.example.steriotypes.GetMapping.class);
               String methodEndpoint    = annotation.value();
               String methodName        = method.getName();

               this.controllerLookupTable.put(
                       new RequestInfo("GET", methodEndpoint),
                       new ControllerMeta(clazz, methodName)
               );
           }

           if(method.isAnnotationPresent(org.example.steriotypes.PostMapping.class)) {
               PostMapping annotation    = method.getAnnotation(org.example.steriotypes.PostMapping.class);
               String methodEndpoint    = annotation.value();
               String methodName        = method.getName();

               this.controllerLookupTable.put(
                       new RequestInfo("POST", methodEndpoint),
                       new ControllerMeta(clazz, methodName)
               );
           }


           if(method.isAnnotationPresent(org.example.steriotypes.PutMapping.class)) {
               PutMapping annotation    = method.getAnnotation(org.example.steriotypes.PutMapping.class);
               String methodEndpoint    = annotation.value();
               String methodName        = method.getName();

               this.controllerLookupTable.put(
                       new RequestInfo("PUT", methodEndpoint),
                       new ControllerMeta(clazz, methodName)
               );
           }

           if(method.isAnnotationPresent(org.example.steriotypes.DeleteMapping.class)) {
               DeleteMapping annotation = method.getAnnotation(org.example.steriotypes.DeleteMapping.class);
               String methodEndpoint    = annotation.value();
               String methodName        = method.getName();

               this.controllerLookupTable.put(
                       new RequestInfo("DELETE", methodEndpoint),
                       new ControllerMeta(clazz, methodName)
               );
           }
       }



//
//       Controller annotation   = (Controller) clazz.getAnnotation(Controller.class);
//       String httpMethod       = annotation.method();
//       String httpEndpoint     = annotation.endpoint();
//
//       this.controllerLookupTable.put(
//               new RequestInfo(httpMethod, httpEndpoint),
//               clazz
//       );
   }

}
