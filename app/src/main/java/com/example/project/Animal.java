package com.example.project;

public class Animal {

    // Fields
    private AuxData auxdata;
    private String name;
    private int company;
    private String location;
    private String category;
    private int size;
    private int cost;

    // Constructor
    public Animal(String name, int company, String location, String category, int size, int cost, AuxData auxData) {
        this.name = name;
        this.company = company;
        this.location = location;
        this.category = category;
        this.size = size;
        this.cost = cost;
        this.auxdata = auxData;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public int getCompany(){
        return company;
    }

    public String getLocation() {
        return location;
    }

    public String getCategory() {
        return category;
    }

    public int getSize() {
        return size;
    }

    public int getCost() {
        return cost;
    }

    public AuxData getAuxdata() {
        return auxdata;
    }

    public void setAuxdata(AuxData auxdata) {
        this.auxdata = auxdata;
    }

    // Converts the data to string
    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + company +
                ", location='" + location + '\'' +
                ", color='" + category + '\'' +
                ", length=" + size + '\'' +
                ", weight=" + cost + '\'' +
                ", auxData=" + auxdata +
                '}';
    }
}