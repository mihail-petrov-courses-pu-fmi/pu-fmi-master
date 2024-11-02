package org.example;

import org.example.controllers.CustomerController;
import org.example.controllers.HomeController;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    private static final String NEW_LINE = "\n\r";

    private static String buildHTTPResponse(String body) {

        return "HTTP/1.1 200 OK" + NEW_LINE +
                "Access-Control-Allow-Origin: *" + NEW_LINE +
                "Content-Length: " + body.getBytes().length +  NEW_LINE +
                "Content-Type: text/html" + NEW_LINE + NEW_LINE +
                body + NEW_LINE + NEW_LINE;
    }

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket   = new ServerSocket(1423);

        while(serverSocket.isBound()) {
            Socket clientSocket         =  serverSocket.accept();

            InputStream request     =  clientSocket.getInputStream();
            OutputStream response   = clientSocket.getOutputStream();

            // 1. Ще обработим входните данни, които ни идват в ваяката
            // InputStream request
            InputStreamReader reader = new InputStreamReader(request);

            // 2. Ще трябва да опаковаме нашия четец, в допълнителен
            // буферен четец
            BufferedReader httRequestReader = new BufferedReader(reader);

            // 3. Изчитане на заявката
            // - четем ред по ред и обработваме
            String currentLine;

            // ще си направя две променливи за метод и endpoint
            String httpMethod = "";
            String httpEndpoint = "";

            while ((currentLine = httRequestReader.readLine()) != null) {
                String[] httpHeaderTitleCollection = currentLine.split(" ");
                httpMethod      = httpHeaderTitleCollection[0];
                httpEndpoint    = httpHeaderTitleCollection[1];
                break;
            }

            // 4. Ще си направим междинен интерпретатор на заявките
            String controllerMessage = "Controller not found";

            if(httpMethod.equals("GET") && httpEndpoint.equals("/home")) {

                HomeController controller = new HomeController();
                controllerMessage = controller.index();
            }

            if(httpMethod.equals("GET") && httpEndpoint.equals("/customer")) {

                CustomerController controller = new CustomerController();
                controllerMessage = controller.index();
            }

            String message = buildHTTPResponse(controllerMessage);
            response.write(message.getBytes());

            response.close();
            request.close();
            clientSocket.close();
        }
    }
}