package org.example;

import org.example.framework.ApplicationBootstrap;
import org.example.framework.HttpProcessor;
import org.example.framework.HttpServerClassLoader;

import java.io.*;

public class Application {

    public  static void main(String[] args) {

        ApplicationBootstrap.run(Application.class);

//
//        try {
//            HttpServerClassLoader.bootstrapApplication();
//            HttpServerClassLoader.bootstrapServices();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }



//        try {
//            HttpProcessor httpProcessor = new HttpProcessor(3184);
//            httpProcessor.start();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}