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

           if(singleField.isAnnotationPresent(org.example.Documentable.class)) {

               Documentable documentableAnnotation = singleField.getAnnotation(org.example.Documentable.class);
               String fieldName = documentableAnnotation.title().equals("_")
                                    ? singleField.getName()
                                    : documentableAnnotation.title();

               xmlBuilder.append("<")
                       .append(fieldName)
                       .append(">")
                       .append(singleField.get(parsableObject))
                       .append("</")
                       .append(fieldName)
                       .append(">");
           }
       }

       return xmlBuilder.toString();
    }

    // Да се реализира JSON парсър
    // който получава инстанция и връща произволен JSON обект

    // Можем да връщаме JSON стойности само ако КЛАСЪ
    // e анотиран с анотация JSONEntity

    // Всяко поле от класа трябва да има анотация
    // JSONField с две стойства title
    // - име на полето
    // - expected_type  с две стойности STRING и PLAIN
    // -> ако стойноста е STRING резултата е ограден в кавички
    // -> ако стойноста е PLAIN резултат е без кавички
    public static String parseObjectToJson(Object parsableObject) throws IllegalAccessException {

        Class clazz = parsableObject.getClass();
        if(!clazz.isAnnotationPresent(org.example.JSONEntity.class)) {
            return "{}";
        }

        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");

        for(Field singleField : clazz.getDeclaredFields()) {
            singleField.setAccessible(true);

            if(!singleField.isAnnotationPresent(
                    org.example.JSONField.class)) {
                continue;
            }

            JSONField jsonFieldAnnotation = singleField.getAnnotation(org.example.JSONField.class);
            String anotationTitle   =  jsonFieldAnnotation.title().equals("_")
                                        ? singleField.getName()
                                        : jsonFieldAnnotation.title();
            JsonFieldType type      = jsonFieldAnnotation.expectedType();

            jsonBuilder.append("\"")
                    .append(anotationTitle)
                    .append("\"")
                    .append(":");

            if(type == JsonFieldType.STRING) {
                jsonBuilder.append("\"")
                        .append(singleField.get(parsableObject))
                        .append("\"");
            }

            if(type == JsonFieldType.PLAIN) {
                jsonBuilder.append("")
                        .append(singleField.get(parsableObject))
                        .append("");
            }

            jsonBuilder.append(",");
        }

        String result = jsonBuilder.toString();
        String jsonNoComma = result.substring(0, result.length() -1);
        return jsonNoComma + "}";
    }

    public static void main(String[] args) throws IllegalAccessException {

        Item myItem = new Item();
        System.out.println(parseObject(myItem));

        ItemJson jsonItem = new ItemJson();
        System.out.println(parseObjectToJson(jsonItem));
    }
}