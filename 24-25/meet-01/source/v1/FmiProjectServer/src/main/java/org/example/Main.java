package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(1423);
        System.out.println("Server start");

        Socket connectionSocket = serverSocket.accept();
        System.out.println("Waiting for connection");

        // request
        InputStream request     = connectionSocket.getInputStream();
        OutputStream response   = connectionSocket.getOutputStream();

        request.close();
        response.close();
        serverSocket.close();
    }
}