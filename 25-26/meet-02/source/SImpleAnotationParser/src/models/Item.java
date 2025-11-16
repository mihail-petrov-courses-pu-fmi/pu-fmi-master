package models;
import labels.XMLEntity;
import labels.XMLProperty;

@XMLEntity
public class Item {

    @XMLProperty(propertyName = "productName")
    private String name = "Book";

    private String subTitle = "Simple book";

    @XMLProperty
    private double price = 12.0;
}
