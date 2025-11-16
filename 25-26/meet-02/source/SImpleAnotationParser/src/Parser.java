import labels.XMLEntity;
import labels.XMLProperty;
import models.Item;

import java.lang.reflect.Field;

public class Parser {
    public static void main(String[] args) throws IllegalAccessException {

        Item sampleItem = new Item();
        System.out.println(parse(sampleItem));
    }

    public static String parse(Object parsableObject) throws IllegalAccessException {

        // 1. Искам моя парсър да може да вземе произволен
        // class и да го разгледа.

        Class parsableClass = parsableObject.getClass();

        if(!parsableClass.isAnnotationPresent(XMLEntity.class)) {
            return "";
        }


        Field[] fieldCollection = parsableClass.getDeclaredFields();

        StringBuilder stringBuilder = new StringBuilder();

        for(Field field : fieldCollection) {

            field.setAccessible(true);


            if(field.isAnnotationPresent(XMLProperty.class)) {

                String annotationFieldName = field.getAnnotation(XMLProperty.class).propertyName();
                String fieldName = annotationFieldName.equals("_") ?
                            field.getName() :
                            annotationFieldName;

                stringBuilder.append("<")
                        .append(fieldName)
                        .append(">")
                        .append(field.get(parsableObject))
                        .append("</")
                        .append(fieldName)
                        .append(">");
            }
        }

        return stringBuilder.toString();
    }
}
