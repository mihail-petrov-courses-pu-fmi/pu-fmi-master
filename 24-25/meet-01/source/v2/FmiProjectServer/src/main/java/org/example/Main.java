package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {


    public static final int COUNT_BREAK = 10;
    public static final int EXIT_CODE = 1;
    public static final int COMMON_CODE = 9;

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(1423);
        System.out.println("Server start");

        Socket connectionSocket = serverSocket.accept();
        System.out.println("Waiting for connection");

        // request
        InputStream request     = connectionSocket.getInputStream();
        OutputStream response   = connectionSocket.getOutputStream();

        int iterationCount = 0;

        while (connectionSocket.isConnected()) {

            // какво получавам от клиента
            int responseToken = request.read();
            System.out.println("Response " + responseToken);

            if(iterationCount == COUNT_BREAK) {
                response.write(EXIT_CODE);
            }
            else {
                response.write(COMMON_CODE);
            }

            iterationCount++;
        }


        request.close();
        response.close();
        serverSocket.close();
    }
}