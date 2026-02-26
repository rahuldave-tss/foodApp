package services;

import models.*;
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

        System.out.print("Do you want to add more items?(y/n) :");
        String s=validateString();
        if(s.equalsIgnoreCase("y")){
            addItemToCart();
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

        FoodItem itemToRemove = null;

        for (FoodItem food : currentCart.keySet()) {
            if (food.getId() == id) {
                itemToRemove = food;
                break;
            }
        }

        if (itemToRemove != null) {
            currentCart.remove(itemToRemove);
            System.out.println("Item removed successfully.");
        } else {
            System.out.println("Item not found in cart.");
        }

        System.out.print("Do you want to remove more items? (y/n): ");
        String s = validateString();

        if (s.equalsIgnoreCase("y")) {
            removeItemFromCart();
        }
    }

    public void viewCartSummary(){
        order.getCart().displayCart();
    }

    public void confirmOrder() {

        if(order.getCart().getShoppingCart().isEmpty()){
            System.out.println("Cart is empty. Cannot place order.");
            return;
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
                return;
        }

        paymentService.doPayment(total);
        order.setFinalAmount(total);
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




}
