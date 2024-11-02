package org.example;

public class Item {

    @Documentable
    private String name      = "Book";

    @Documentable(title = "sub_title")
    private String subTitle  = "Science";

    @Documentable
    private double price     = 12.0;
}
