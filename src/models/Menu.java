package models;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    List<FoodItem> itemList;

    public Menu() {
        this.itemList=new ArrayList<>();
        initializeMenu();
    }

    private void initializeMenu() {
        itemList.add(new FoodItem("Burger", 50));
        itemList.add(new FoodItem("Pizza", 200));
        itemList.add(new FoodItem("Pasta", 100));
    }

    public List<FoodItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<FoodItem> itemList) {
        this.itemList = itemList;
    }
}
