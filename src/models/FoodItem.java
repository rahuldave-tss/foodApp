package models;

public class FoodItem {
    private static int newId=1;
    private int id;
    private String name;
    private double price;

    public FoodItem(String name, double price) {
        id=newId++;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
