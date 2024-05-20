package com.example.project;

public class AuxData {

    // Fields
    private String img;
    private String info;

    // Getters
    public String getImg() {
        return img;
    }

    public String getInfo() {
        return info;
    }

    // Converts the data to string
    @Override
    public String toString() {
        return "{" +
                "img=" + img + '\'' +
                ", info=" + info +
                '}';
    }
}