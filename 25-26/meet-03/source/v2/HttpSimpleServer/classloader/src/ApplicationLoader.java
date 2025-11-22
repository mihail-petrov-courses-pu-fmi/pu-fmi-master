import labels.SimpleController;
import labels.SimpleGetMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ApplicationLoader {

    // за зареждане на класове, и тяхната обработка.
    // - ще получа имената на всички класове, които се намират
    // - в някакво конкретно пространство от система
    private static final ClassLoader classLoader = ClassLoader.getSystemClassLoader();

    private static HashMap<RequestClassInfo, Class> classMap = new HashMap<>();

    public static Class loadController(String url) {
        return classMap.get(new RequestClassInfo(url));
    }

    // Откриване на всички файлове в проекта
    public static void run(String rootProjectPath) throws IOException, ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        // class loader

        String resourcePath = rootProjectPath.replace(".", "/");
        InputStream stream =  classLoader.getResourceAsStream(resourcePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        // ще премине през всички ресурси, които се намират в този пакет към момента
        String classPath = "";
        while((classPath = reader.readLine()) != null) {

            String fullClassPath = rootProjectPath + "." + classPath;

            // Java файл е всеки файл който има разширение .class
            // всичко останало е потенциално контейнер (папка), ако го срещнем, продължаваме
            // надоло
            if(!classPath.contains(".class")) {
                run(fullClassPath);
                continue;
            }

            // тук ще се пази името на класа
            String className        = fullClassPath.replace(".class", "");
            Class classReference    =  Class.forName(className);

            // Правим си нова инстанция в опит да получим някакви данни от система
//            var controllerInstance = classReference.newInstance();

            // 1. Не можем да вземем директно данни от анотация която е сложена върху КЛАС
            // 2. Освен ако не направим точен каст

            // ИСкам да работя само и единствено с КЛАСОВЕ, които имат анотация
            // че са КОНТРОЛЕРИ или иначе казано SimpleController
            if(classReference.isAnnotationPresent(SimpleController.class)) {

                SimpleController simpleControllerAnnotation = (SimpleController) classReference.getAnnotation(SimpleController.class);
                String url = simpleControllerAnnotation.url();
                classMap.put(new RequestClassInfo(url), classReference);
            }
        }
    }
}
