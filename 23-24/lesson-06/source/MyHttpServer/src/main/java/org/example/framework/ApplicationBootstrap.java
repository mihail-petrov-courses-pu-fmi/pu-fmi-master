package org.example.framework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ApplicationBootstrap {

    public static void run(Class applicationClass) {
        Package applicationLevelPackage =  applicationClass.getPackage();
        String rootPath = applicationLevelPackage.getName().replace(".", "/");

        findAllApplicationComponents(rootPath);
    }

    public static void findAllApplicationComponents(String path) {

        BufferedReader applicationClassStream = loadClass(path);

        try {
            String resourceLine = "";
            while((resourceLine = applicationClassStream.readLine()) != null) {

                if(isPath(resourceLine)) {

                    String resourcePath = path + "/" + resourceLine;
                    findAllApplicationComponents(resourcePath);
                }

                if(isClass(resourceLine)) {
                    loadComponent(path, resourceLine);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadComponent(String resourcePath, String classId) {

        try {
            String classPath = resourcePath.replace("/", ".");
            String className = classId.replace(".class", "");
            String classFullPath = classPath + "." + className;

            Class classReference = Class.forName(classFullPath);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private static BufferedReader loadClass(String path) {

        ClassLoader appLoader                 = ClassLoader.getSystemClassLoader();
        InputStream appLoaderStream           =  appLoader.getResourceAsStream(path);
        return new BufferedReader(new InputStreamReader(appLoaderStream));
    }


    private static boolean isClass(String resourceId) {
        return resourceId.contains(".class");
    }

    private static boolean isPath(String resourceId) {
        return !isClass(resourceId);
    }
}
