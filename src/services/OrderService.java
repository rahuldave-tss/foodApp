package services;

import models.*;
import repos.DiscountRepo;

import java.util.List;

import static utils.Validate.*;

public class OrderService {
    //for customer
    private MenuService menuService;
    private DiscountRepo discountRepo;
    private Order order;
    private IDiscountService discountService;
    private IPaymentService paymentService;

    public OrderService() {
        this.menuService=new MenuService();
        this.discountService=new AmountDiscountService();
        this.discountRepo=new DiscountRepo();
        this.order=new Order();
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

    public void removeItemFromCart(){
        if(order.getCart().getShoppingCart().isEmpty()){
            System.out.println("Cart is Empty !!");
            return;
        }
        System.out.print("Enter the FoodItem ID to remove: ");
        int id=validateInt();
        FoodItem foodItem=menuService.findFoodItemById(id);
        if(foodItem==null){
            System.out.println("Invalid ID...");
            return;
        }

        List<OrderItem> currentCart=order.getCart().getShoppingCart();
        for(OrderItem oi:currentCart){
            if(oi.getFoodItem().getId()==id){
                currentCart.remove(oi);
                break;
            }
        }
        order.getCart().setShoppingCart(currentCart);

        System.out.print("Do you want to remove more items?(y/n) :");
        String s=validateString();
        if(s.equalsIgnoreCase("y")){
            removeItemFromCart();
        }
    }

    public void viewCartSummary(){
        order.getCart().displayCart();
    }

    public void confirmOrder(){
        //confirm order payment and assign delivery partner
        System.out.println("Enter Payment Mode: ");
        System.out.println("1. Cash");
        System.out.println("2. Upi");
        System.out.print("Enter your choice: ");
        int choice=validateInt();
        switch (choice){
            case 1:{
                paymentService=PaymentFactory.getPaymentService(PaymentType.CASH);
                paymentService.doPayment(calculateTotal());
                break;
            }
            case 2:{
                paymentService=PaymentFactory.getPaymentService(PaymentType.UPI);
                paymentService.doPayment(calculateTotal());
                break;
            }
            default:{
                System.out.println("Not a valid option....");
            }
        }
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
