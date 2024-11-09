package org.example;

import org.example.system.ApplicationLoader;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;

public class FrameworkApplication {

    private static final String NEW_LINE = "\n\r";
    private static  ApplicationLoader appLoader = new ApplicationLoader();

    public static void run(Class mainClass) {

        try {
            bootstrap(mainClass);
            startWebServer();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String buildHTTPResponse(String body) {

        return "HTTP/1.1 200 OK" + NEW_LINE +
                "Access-Control-Allow-Origin: *" + NEW_LINE +
                "Content-Length: " + body.getBytes().length +  NEW_LINE +
                "Content-Type: text/html" + NEW_LINE + NEW_LINE +
                body + NEW_LINE + NEW_LINE;
    }

    // Трябва да получаваме MAIN класа и от него
    // да извлечем информация за всички пакети
    // в клиентското приложение
    private static void bootstrap(Class mainClass) throws IOException, ClassNotFoundException {
        appLoader.findAllClasses(mainClass.getPackageName());
    }

    // Да стартираме сървъра и да чакаме за заявки
    private static void startWebServer() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        ServerSocket serverSocket   = new ServerSocket(1423);
        System.out.println("Listening on port 1423");

        while(serverSocket.isBound()) {
            Socket clientSocket         =  serverSocket.accept();

            InputStream request     =  clientSocket.getInputStream();
            OutputStream response   = clientSocket.getOutputStream();


            InputStreamReader reader = new InputStreamReader(request);

            BufferedReader httRequestReader = new BufferedReader(reader);

            String currentLine;

            String httpMethod = "";
            String httpEndpoint = "";

            while ((currentLine = httRequestReader.readLine()) != null) {
                String[] httpHeaderTitleCollection = currentLine.split(" ");
                httpMethod      = httpHeaderTitleCollection[0];
                httpEndpoint    = httpHeaderTitleCollection[1];
                break;
            }

            String controllerMessage = appLoader.executeController(httpMethod, httpEndpoint);
            String message = buildHTTPResponse(controllerMessage);
            response.write(message.getBytes());

            response.close();
            request.close();
            clientSocket.close();
        }
    }
}
