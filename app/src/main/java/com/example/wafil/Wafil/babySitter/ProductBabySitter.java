package com.example.wafil.Wafil.babySitter;

public class ProductBabySitter {
    private String name;
    private String email;
    private String image;

    public ProductBabySitter(String name, String email, String image) {
        this.name = name;
        this.email = email;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getEmail() { return email; }

    public String getImage() { return image; }
}
