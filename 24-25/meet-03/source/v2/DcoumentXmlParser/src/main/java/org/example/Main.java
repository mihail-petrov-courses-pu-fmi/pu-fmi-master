package org.example;

import java.lang.reflect.Field;

public class Main {

    public static String parseObject(Object parsableObject) throws IllegalAccessException {
        // 1. Взимам си обекта с който ще работя
        // и му искам + получавам данни за класа

       Class parsableObjectClass = parsableObject.getClass();
       Field[] fieldCollection = parsableObjectClass.getDeclaredFields();

       // Ще си направим STring builder който да ни пази всички стойности,
        // за XML документа
        StringBuilder xmlBuilder = new StringBuilder();

       for(Field singleField : fieldCollection) {

           singleField.setAccessible(true);

           xmlBuilder.append("<")
                     .append(singleField.getName())
                     .append(">")
                     .append(singleField.get(parsableObject))
                     .append("</")
                     .append(singleField.getName())
                     .append(">");
       }

       return xmlBuilder.toString();
    }

    public static void main(String[] args) throws IllegalAccessException {

        Item myItem = new Item();
        System.out.println(parseObject(myItem));
    }
}