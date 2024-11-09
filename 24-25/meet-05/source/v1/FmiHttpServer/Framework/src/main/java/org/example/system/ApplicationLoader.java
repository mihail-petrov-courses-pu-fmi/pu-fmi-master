package org.example.system;

import org.example.steriotypes.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Objects;

public class ApplicationLoader {

    public static class RequestInfo {

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

    private ClassLoader classLoader = ClassLoader.getSystemClassLoader();

    // Ще си направя речник, в който да пазя комбинация от HTTP method HTTP endpoint и
    // класа, който трябва да го обработи

    private HashMap<RequestInfo, Class> controllerLookupTable = new HashMap<RequestInfo, Class>();

    // ИЗпълнение на контролера
    public String executeController(String httpMethod, String httpEndpoint) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class clazz = this.controllerLookupTable.get(new RequestInfo(httpMethod, httpEndpoint));

        if(clazz == null) {
            return "";
        }

        // ТУК ИМАМЕ ИНСТАНЦИЯ
        var controllerInstance =  clazz.getDeclaredConstructor().newInstance();
        return (String) clazz.getMethod("index").invoke(controllerInstance);
    }

    // 1. Да преровим и да открием всички анотации

    // От тук започваме да търсим класове, които се намерит
    // в пакета и в свързани с него пакети.
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

                String className        = packageReference.replace(".class", "");
                String fullClassName    = packageName + "." + className;
                Class clazz             =  Class.forName(fullClassName);

                if(clazz.isAnnotationPresent(org.example.steriotypes.Controller.class)) {
                    Controller annotation   = (Controller) clazz.getAnnotation(Controller.class);
                    String httpMethod       = annotation.method();
                    String httpEndpoint     = annotation.endpoint();

                    this.controllerLookupTable.put(
                            new RequestInfo(httpMethod, httpEndpoint),
                            clazz
                    );
                }
            }
        }
   }
}
