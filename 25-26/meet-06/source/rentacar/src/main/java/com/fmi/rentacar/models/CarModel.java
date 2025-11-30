package com.fmi.rentacar.models;

public class CarModel {

    public static final String TABLE = "td_cars";

    public static class columns {
        public static final String ID               = "id";
        public static final String IS_ENABLED       = "is_enabled";

        public static final String TITLE            = "title";
        public static final String MODEL            = "model";
        public static final String TYPE             = "type";
        public static final String PRICE_FOR_RENT   = "price_for_rent";
    }

    private int id;
    private String title;
    private String model;
    private String type;
    private double priceForRent;
    private boolean isEnabled;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPriceForRent() {
        return priceForRent;
    }

    public void setPriceForRent(double priceForRent) {
        this.priceForRent = priceForRent;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
