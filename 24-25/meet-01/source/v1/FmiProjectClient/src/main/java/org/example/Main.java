package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {

        Socket clientSocket = new Socket("localhost", 1423);

        InputStream response = clientSocket.getInputStream();
        OutputStream request = clientSocket.getOutputStream();

        response.close();
        request.close();
        clientSocket.close();
    }
}