import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ApplicationLoader {

    // за зареждане на класове, и тяхната обработка.
    // - ще получа имената на всички класове, които се намират
    // - в някакво конкретно пространство от система
    private static final ClassLoader classLoader = ClassLoader.getSystemClassLoader();

    // Откриване на всички файлове в проекта
    public static void run(String rootProjectPath) throws IOException {
        // class loader

        String resourcePath = rootProjectPath.replace(".", "/");
        InputStream stream =  classLoader.getResourceAsStream(resourcePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        // ще премине през всички ресурси, които се намират в този пакет към момента
        String classPath = "";
        while((classPath = reader.readLine()) != null) {

            // Java файл е всеки файл който има разширение .class
            // всичко останало е потенциално контейнер (папка), ако го срещнем, продължаваме
            // надоло
            if(!classPath.contains(".class")) {
                run(rootProjectPath + "." + classPath);
            }

            System.out.println(classPath);
        }
    }
}
