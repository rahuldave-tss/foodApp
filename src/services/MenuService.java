package services;

import models.FoodItem;
import models.Menu;
import models.OrderItem;

import java.util.List;

public class MenuService {
    private Menu menu;

    public MenuService() {
        this.menu = new Menu();
    }

    public Menu getMenu() {
        return menu;
    }

    //returns id
    public int addFoodItem(String name, double price){
        FoodItem foodItem=findFoodItemByName(name);
        if(foodItem==null){
            FoodItem newFoodItem=new FoodItem(name,price);
            List<FoodItem> itemList=menu.getItemList();
            itemList.add(newFoodItem);
            menu.setItemList(itemList);
            return newFoodItem.getId();
        }
        return -1;
    }

    public boolean removeFoodItem(int id){
        FoodItem foodItem=findFoodItemById(id);
        if(foodItem!=null){
            List<FoodItem> itemList=menu.getItemList();
            itemList.remove(foodItem);
            menu.setItemList(itemList);
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

    public void displayMenu() {
        if (menu.getItemList().isEmpty()) {
            System.out.println("Menu is empty.");
            return;
        }

        System.out.println("-------------------------------------------------");
        System.out.printf("%-10s %-20s %-10s%n", "ID", "NAME", "PRICE");
        System.out.println("-------------------------------------------------");

        for (FoodItem f : menu.getItemList()) {
            System.out.printf("%-10d %-20s %-10.2f%n",
                    f.getId(),
                    f.getName(),
                    f.getPrice());
        }

        System.out.println("-------------------------------------------------");
    }
}
