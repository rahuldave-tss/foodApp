package models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<OrderItem> shoppingCart;

    public Cart() {
        shoppingCart=new ArrayList<>();
    }

    public List<OrderItem> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(List<OrderItem> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void addItems(List<OrderItem> itemList){
        shoppingCart.addAll(itemList);
    }

    public void addItem(OrderItem item){
        shoppingCart.add(item);
    }

    public void removeItem(OrderItem item){
        shoppingCart.remove(item);
    }

    public double getTotal(){
        double cartTotal=0;
        for(OrderItem i:shoppingCart){
            cartTotal+=i.getQuantity()*(i.getFoodItem().getPrice());
        }
        return cartTotal;
    }

    public void displayCart(){
        //display in tabular format
    }

}
