package com.example.kete;

public class Retailer {
    private String name, shop_name, email_id;

    public Retailer() {
    }

    public Retailer(String name, String shop_name, String email_id) {
        this.name = name;
        this.shop_name = shop_name;
        this.email_id = email_id;
    }

    public String getName() {
        return name;
    }

    public String getShop_name() {
        return shop_name;
    }

    public String getEmail_id() {
        return email_id;
    }
}
