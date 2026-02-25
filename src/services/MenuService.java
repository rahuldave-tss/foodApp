package services;

import models.FoodItem;
import models.Menu;

public class MenuService {
    private Menu menu;

    public MenuService() {
        this.menu = new Menu();
    }

    public boolean addFoodItem(String name, double price){
        FoodItem foodItem=findFoodItemByName(name);
        if(foodItem==null){
            menu.getItemList().add(new FoodItem(name, price));
            return true;
        }
        return false;
    }

    public boolean removeFoodItem(String name){
        FoodItem foodItem=findFoodItemByName(name);
        if(foodItem!=null){
            menu.getItemList().remove(foodItem);
            return true;
        }
        return false;
    }

    public FoodItem findFoodItemByName(String name) {
        for(FoodItem f:menu.getItemList()){
            if(f.getName().toLowerCase().equals(name))return f;
        }
        return null;
    }

    public FoodItem findFoodItemById(int id){
        for(FoodItem f:menu.getItemList()){
            if(f.getId()==id)return f;
        }
        return null;
    }

    public void displayMenu(){
        //display in tabular format
    }
}
