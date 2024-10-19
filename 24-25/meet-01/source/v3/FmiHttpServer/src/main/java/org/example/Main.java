package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            String message = buildHTTPResponse("Hello World");
            response.write(message.getBytes());

            response.close();
            request.close();
            clientSocket.close();
        }
    }
}