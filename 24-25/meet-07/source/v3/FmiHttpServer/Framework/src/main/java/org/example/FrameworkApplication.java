package org.example;

import org.example.entities.RequestInfo;
import org.example.system.ApplicationLoader;
import org.example.system.HttpProcessor;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;

public class FrameworkApplication {

    private static final String NEW_LINE = "\r\n";
    private static HttpProcessor httpProcessor = new HttpProcessor();

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
        ApplicationLoader.getInstance().findAllClasses(mainClass.getPackageName());
    }

    private static RequestInfo parseHttpRequest(InputStream inputStream) throws IOException {

        InputStreamReader reader        = new InputStreamReader(inputStream);
        BufferedReader httRequestReader = new BufferedReader(reader);

        String currentLine;
        boolean isBodyProcessable   = false;
        RequestInfo httpRequest     =  new RequestInfo();

        while ((currentLine = httRequestReader.readLine()) != null) {

            // Започваме парсване на BODY часта от нашата заявка
            if(currentLine.isEmpty()) {
                isBodyProcessable = true;
                break;
            }

            if(!httpRequest.hasMethodAndEndpoint()) {

                String[] httpHeaderTitleCollection = currentLine.split(" ");
                httpRequest.setHttpMethod(httpHeaderTitleCollection[0]);
                httpRequest.setHttpEndpoint(httpHeaderTitleCollection[1]);
                continue;
            }

            String[] headerParseCollection = currentLine.split(": ");
            String headerKey    = headerParseCollection[0];
            String headerValue  = headerParseCollection[1];
            httpRequest.setHeader(headerKey, headerValue);
        }

        if(isBodyProcessable && httpRequest.hasContent()) {

            int length = httpRequest.getContentLength();
            char[] bodyCharacterCollection = new char[length];
            httRequestReader.read(bodyCharacterCollection,0, length);

            // трансформация на масив от символи към низ
            StringBuilder bodyBuilder = new StringBuilder();
            bodyBuilder.append(bodyCharacterCollection);
            httpRequest.setHttpBody(bodyBuilder.toString());
        }

        return httpRequest;
    }

    // Да стартираме сървъра и да чакаме за заявки
    private static void startWebServer() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        ServerSocket serverSocket   = new ServerSocket(1423);
        System.out.println("Listening on port 1423");

        while(serverSocket.isBound()) {
            Socket clientSocket         =  serverSocket.accept();

            InputStream request         =  clientSocket.getInputStream();
            OutputStream response       = clientSocket.getOutputStream();

            var httpRequest             = parseHttpRequest(request);

            if(httpRequest.isEmpty()) {
                continue;
            }

            String controllerMessage    = httpProcessor.executeController(httpRequest);

            String message              = buildHTTPResponse(controllerMessage);
            response.write(message.getBytes());

            response.close();
            request.close();
            clientSocket.close();
        }
    }
}
