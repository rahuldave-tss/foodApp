package services;

import models.*;
import notifications.CustomerNotification;
import repos.DiscountRepo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static utils.Validate.*;

public class OrderService {
    //for customer
    private MenuService menuService;
    private DiscountRepo discountRepo;
    private Order order;
    private IDiscountService discountService;
    private IPaymentService paymentService;

    public OrderService(MenuService menuService) {
        this.menuService=menuService;
        this.discountService=new AmountDiscountService();
        this.discountRepo=new DiscountRepo();
        this.order=new Order();
    }

    public Order getOrder() {
        return order;
    }

    public void addItemToCart(){
        System.out.print("Enter the FoodItem ID to add: ");
        int id=validateInt();
        FoodItem foodItem=menuService.findFoodItemById(id);
        if(foodItem==null){
            System.out.println("Invalid ID...");
            return;
        }
        System.out.print("Enter quantity: ");
        int quantity=validateInt();

        OrderItem orderItem=new OrderItem(foodItem,quantity);
        order.getCart().addItem(orderItem);

        while(true){
             System.out.print("Do you want to add more items?(y/n) :");
             String s=validateString();
             if(s.equalsIgnoreCase("y")){
                 addItemToCart();
                 break;
             }
             else if(s.equalsIgnoreCase("n")){
                 break;
             }
             else{
                 System.out.println("Invalid input !!");
             }
        }

    }

    public void removeItemFromCart() {

        Map<FoodItem, OrderItem> currentCart = order.getCart().getShoppingCart();

        if (currentCart.isEmpty()) {
            System.out.println("Cart is Empty !!");
            return;
        }

        System.out.print("Enter the FoodItem ID to remove: ");
        int id = validateInt();

        FoodItem itemToModify = null;

        for (FoodItem food : currentCart.keySet()) {
            if (food.getId() == id) {
                itemToModify = food;
                break;
            }
        }

        if (itemToModify != null) {

            OrderItem orderItem = currentCart.get(itemToModify);
            int currentQty = orderItem.getQuantity();

            System.out.println("Current quantity in cart: " + currentQty);
            System.out.print("Enter quantity to remove: ");
            int qtyToRemove = validateInt();

            if (qtyToRemove <= 0) {
                System.out.println("Invalid quantity!");
            }
            else if (qtyToRemove < currentQty) {
                orderItem.setQuantity(currentQty - qtyToRemove);
                System.out.println("Quantity updated successfully.");
            }
            else if (qtyToRemove == currentQty) {
                currentCart.remove(itemToModify);
                System.out.println("Item removed completely from cart.");
            }
            else {
                System.out.println("You cannot remove more than existing quantity!");
            }

        } else {
            System.out.println("Item not found in cart.");
        }

        while (true) {
            System.out.print("Do you want to remove more items? (y/n): ");
            String s = validateString();

            if (s.equalsIgnoreCase("y")) {
                removeItemFromCart();
                break;
            }
            else if (s.equalsIgnoreCase("n")) {
                break;
            }
            else {
                System.out.println("Invalid input !!");
            }
        }
    }

    public void viewCartSummary(){
        order.getCart().displayCart();
    }

    public boolean confirmOrder() {

        if(order.getCart().getShoppingCart().isEmpty()){
            System.out.println("Cart is empty. Cannot place order.");
            return false;
        }

        System.out.println("\n------ ORDER SUMMARY ------");
        order.getCart().displayCart();

        double total = calculateTotal();
        double discount = getDiscountAmount();

        System.out.printf("Discount Applied: %.2f%n", discount);
        System.out.printf("Final Payable Amount: %.2f%n", total);

        System.out.println("\nEnter Payment Mode:");
        System.out.println("1. Cash");
        System.out.println("2. UPI");
        System.out.print("Enter your choice: ");

        int choice = validateInt();

        switch (choice){
            case 1:
                paymentService = PaymentFactory.getPaymentService(PaymentType.CASH);
                break;
            case 2:
                paymentService = PaymentFactory.getPaymentService(PaymentType.UPI);
                break;
            default:
                System.out.println("Invalid option.");
                return false;
        }

        paymentService.doPayment(total);
        order.setFinalAmount(total);
        return true;
    }

    public double cartTotal(){
        return order.getCart().getTotal();
    }

    public double getDiscountAmount(){
        return discountService.applyDiscount(order.getCart());
    }

    public double calculateTotal(){
        double fixedAmount=cartTotal();
        double discountAmount=getDiscountAmount();

        return fixedAmount-discountAmount;
    }

    public void resetOrder() {
        this.order = new Order();
    }


}
