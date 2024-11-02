package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        MyClassLoader.findAllClasses("org.example");

        ServerSocket serverSocket   = new ServerSocket(3219);

        while(serverSocket.isBound() && !serverSocket.isClosed()) {

            Socket incomingRequest      = serverSocket.accept();
            InputStream inputStream     = incomingRequest.getInputStream();
            OutputStream outputStream   = incomingRequest.getOutputStream();

            // Parse the request
            InputStreamReader reader        = new InputStreamReader(inputStream);
            BufferedReader bufferedReader   = new BufferedReader(reader);

            //
            String currentStreamString;
            String httpMethod   = "";
            String httpEndpoint = "";
            while((currentStreamString = bufferedReader.readLine()) != null ) {

                var httpElementCollection = currentStreamString.split(" ");
                httpMethod   = httpElementCollection[0];
                httpEndpoint = httpElementCollection[1];
                 break;
            }

            if(!httpMethod.isEmpty() || !httpEndpoint.isEmpty()) {
                 var result = MyClassLoader.executePath(httpMethod, httpEndpoint);
                 System.out.println(result);
            }

            inputStream.close();
            outputStream.close();
        }
    }
}