package models;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    List<FoodItem> itemList;

    public Menu() {
        this.itemList=new ArrayList<>();
    }

    public List<FoodItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<FoodItem> itemList) {
        this.itemList = itemList;
    }
}
