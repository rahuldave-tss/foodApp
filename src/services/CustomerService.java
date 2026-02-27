package services;

import exceptions.EmptyMenuException;
import models.*;
import repos.DPRepo;
import repos.OrderRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static utils.Validate.validateInt;

public class CustomerService {
    private MenuService menuService;
    private OrderService orderService;
    private DPRepo dpRepo;
    private User user;
    private OrderRepo orderRepo;

    public CustomerService(MenuService menuService, OrderService orderService, DPRepo dpRepo,User user,OrderRepo orderRepo) {
        this.menuService = menuService;
        this.orderService = orderService;
        this.dpRepo = dpRepo;
        this.user=user;
        this.orderRepo=orderRepo;
        //to show order history and assign delivery partner to the order, we need to set the customer in order service
        orderService.getOrder().setCustomer(user);
    }

    public void displayFeatures(){
        while(true){
            System.out.println("===Customer DashBoard===");
            System.out.println("1. Add Items to cart");
            System.out.println("2. Remove Items from cart");
            System.out.println("3. View Cart Summary");
            System.out.println("4. Place Order");
            System.out.println("5. View Order History");
            System.out.println("6. Back");
            System.out.print("Enter your choice: ");
            int choice=validateInt();

            switch (choice){
                case 1:{
                    displayMenu();
                    orderService.addItemToCart();
                    break;
                }
                case 2:{
                    orderService.viewCartSummary();
                    orderService.removeItemFromCart();
                    break;
                }
                case 3:{
                    orderService.viewCartSummary();
                    break;
                }
                case 4:{
                    boolean confirm=orderService.confirmOrder();
                    if(!confirm){
                        System.out.println("Order Cancelled !!");
                        break;
                    }
                    assignDeliveryPartner();
                    printInvoice();
                    //before resetting order, we need to add the completed order to order repository so that customer can view it in order history and delivery partner can view it in their order history as well
                    Order completedOrder = orderService.getOrder();
                    completedOrder.setCustomer(user);
                    orderRepo.addOrder(completedOrder);
                    orderService.resetOrder();
                    break;
                }
                case 5:{
                    viewOrderHistory();
                    break;
                }
                case 6:{
                    System.out.println("Back to Main Menu...");
                    return;
                }
                default:{
                    System.out.println("Enter a valid choice !!");
                }
            }
        }


    }

    private void displayMenu(){
        try{
            menuService.displayMenu();
        }
        catch(EmptyMenuException e){
            System.out.println(e.getMessage());
        }
    }

    public void assignDeliveryPartner() {

        Order order = orderService.getOrder();

        List<User> partners = dpRepo.getDeliveryPartners();

        if (partners == null || partners.isEmpty()) {
            System.out.println("No delivery partners available.");
            return;
        }

        Random random = new Random();
        User partner = partners.get(random.nextInt(partners.size()));

        order.setDeliveryPartner(partner);

        dpRepo.getDeliveryPartnerOrders(partner).add(order);

        System.out.println("\nDelivery Partner Assigned: " + partner.getName());

        simulateDelivery(order);
    }

    private void simulateDelivery(Order order) {

        try{
            System.out.println("Food is being prepared...");
            Thread.sleep(2000);

            order.setStatus(OrderStatus.PREPARING);
            System.out.println("Status: " + order.getStatus());

            Thread.sleep(2000);

            order.setStatus(OrderStatus.ON_THE_WAY);
            System.out.println("Status: " + order.getStatus());

            Thread.sleep(2000);

            order.setStatus(OrderStatus.DELIVERED);
            System.out.println("Status: " + order.getStatus());

            System.out.println("Order Delivered Successfully !!");
        }
        catch(InterruptedException e){
            System.out.println("Error in simulating delivery: " + e.getMessage());
        }

    }

    public void printInvoice() {

        Order order = orderService.getOrder();
        Cart cart = order.getCart();

        if (cart == null || cart.getShoppingCart().isEmpty()) {
            System.out.println("Cart is empty. No invoice to generate.");
            return;
        }

        Map<FoodItem,OrderItem> items = cart.getShoppingCart();

        System.out.println("\n==================================================================");
        System.out.println("                             INVOICE                          ");
        System.out.println("==================================================================");
        System.out.println("Order ID       : " + order.getOrderId());
        System.out.println("Order Status   : " + order.getStatus());
        System.out.println("Delivery Agent : " +
                (order.getDeliveryPartner() != null
                        ? order.getDeliveryPartner().getName()
                        : "Not Assigned"));
        System.out.println("==================================================================");

        System.out.printf("%-5s %-22s %-8s %-10s %-12s%n",
                "No", "Item", "Qty", "Price", "Subtotal");

        System.out.println("------------------------------------------------------------------");

        double grandTotal = 0;
        int index = 1;

        for (OrderItem item : items.values()) {

            String name = item.getFoodItem().getName();
            double price = item.getFoodItem().getPrice();
            int qty = item.getQuantity();
            double subtotal = price * qty;

            grandTotal += subtotal;

            System.out.printf("%-5d %-22s %-8d %-10.2f %-12.2f%n",
                    index++, name, qty, price, subtotal);
        }

        System.out.println("------------------------------------------------------------------");

        double discount = orderService.getDiscountAmount();
        double finalAmount = orderService.calculateTotal();

        System.out.printf("%-48s %-12.2f%n", "Grand Total:", grandTotal);
        System.out.printf("%-48s %-12.2f%n", "Discount Applied:", discount);
        System.out.printf("%-48s %-12.2f%n", "Final Payable Amount:", finalAmount);

        System.out.println("==================================================================");
        System.out.println("              Thank You For Ordering With Us !!");
        System.out.println("==================================================================\n");
    }

    public void viewOrderHistory() {

        List<Order> history = orderRepo.getOrdersByCustomer(user);

        if (history == null || history.isEmpty()) {
            System.out.println("\nNo past orders found.\n");
            return;
        }

        System.out.println("\n================ ORDER HISTORY ================");

        int index = 1;

        for (Order order : history) {

            System.out.println("\n------------------------------------------------");
            System.out.println("Order #" + index++);
            System.out.println("Order ID       : " + order.getOrderId());
            System.out.println("Order Status   : " + order.getStatus());
            System.out.println("Delivered By   : " +
                    (order.getDeliveryPartner() != null
                            ? order.getDeliveryPartner().getName()
                            : "Not Assigned"));

            double total = order.getFinalAmount();
            System.out.println("Total Amount   : " + total);

            System.out.println("------------------------------------------------");
        }

        System.out.println("================================================\n");
    }

}
