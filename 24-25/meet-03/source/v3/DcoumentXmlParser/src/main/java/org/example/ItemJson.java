package org.example;

@JSONEntity
public class ItemJson {

    @JSONField
    private String name      = "Book";

    @JSONField(title = "sub_title")
    private String subTitle  = "Science";

    @JSONField(expectedType = JsonFieldType.PLAIN)
    private double price     = 12.0;
}
