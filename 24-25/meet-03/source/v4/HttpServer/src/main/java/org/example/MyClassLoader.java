package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ClassLoader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Objects;

public class MyClassLoader {

    static class RequestInfo {
        public String url;
        public String method;

        public RequestInfo(String url, String method) {
            this.url = url;
            this.method = method;
        }

        @Override
        public int hashCode() {
            System.out.println("HASHCODE ISSSS");
            return Objects.hash(this.url, this.method);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            RequestInfo myKey = (RequestInfo) obj;
            return Objects.equals(url, myKey.url) && Objects.equals(method, myKey.method);
        }
    }

    public static String executePath(String method, String url) {
        Class result = classMap.get(new RequestInfo(url, method));

        try {
            var instance = result.getDeclaredConstructor().newInstance();
            return (String)result.getMethod("processData").invoke(instance);

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


    private static HashMap<RequestInfo, Class> classMap = new HashMap<RequestInfo, Class>();

    private static ClassLoader loader      = ClassLoader.getSystemClassLoader();

    public static void findAllClasses(String packageName) throws IOException, ClassNotFoundException {

        InputStream stream      = loader.getResourceAsStream(packageName.replace('.', '/'));

        BufferedReader reader   = new BufferedReader(new InputStreamReader(stream));

        String s = "";
        while ((s = reader.readLine()) != null) {

            if(!s.contains(".class")) {
                findAllClasses(packageName + "." + s);
                continue;
            }

            if(s.contains("ProcessController")) {
                String className    = s.replace(".class", "");
                String classPath    = packageName + "." + className;
                Class classInstance = Class.forName(classPath);

                classMap.put(new RequestInfo("/test", "GET"), classInstance);
            }
        }
    }
}
