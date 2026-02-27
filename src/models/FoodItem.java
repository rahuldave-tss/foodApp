package models;

import utils.RandomNumberGenerator;

public class FoodItem {
    private int id;
    private String name;
    private double price;

    public FoodItem(String name, double price) {
        id=RandomNumberGenerator.generateRandomNumber();
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


    @Override
    public String toString() {
        return "FoodItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FoodItem)) return false;

        FoodItem that = (FoodItem) o;
        return this.id == that.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
