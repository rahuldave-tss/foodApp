package services;

import exceptions.EmptyCartException;
import exceptions.EmptyMenuException;
import exceptions.NoDeliveryPartnerAvailableException;
import exceptions.NoOrdersFoundException;
import models.*;
import repos.DPRepo;
import repos.DiscountRepo;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static utils.Validate.validateInt;

public class CustomerService {
    private DPRepo dpRepo;
    private Customer customer;
    private DiscountRepo discountRepo;
    private IPaymentService paymentService;
    private DiscountService discountService;

    public CustomerService(DPRepo dpRepo,DiscountRepo discountRepo,User customer,DiscountService discountService) {
        this.dpRepo = dpRepo;
        this.discountRepo=discountRepo;
        this.customer=(Customer) customer;
        this.discountService=discountService;
    }

    public void placeOrder(){
        Order order=createNewOrderFromCart();
        takePaymentFromCustomer(order);
        assignDeliveryPartner(order);
        printInvoice(order);
        customer.getCart().clear();
    }

    private void takePaymentFromCustomer(Order order) {
        System.out.println();
        System.out.print("Which type of Payment you want to opt?: ");
        System.out.println("1. Cash");
        System.out.println("2. UPI");
        IPaymentService iPaymentService=null;
        boolean flag=false;
        while (true){
            System.out.print("Enter your choice: ");
            int choice=validateInt();
            switch (choice){
                case 1:{
                    iPaymentService=PaymentFactory.getPaymentService(PaymentType.CASH);
                    flag=true;
                    break;
                }
                case 2:{
                    iPaymentService=PaymentFactory.getPaymentService(PaymentType.UPI);
                    flag=true;
                    break;
                }
                default:
                    System.out.println("Enter a valid choice !!");
            }
            if(flag)break;
        }
        iPaymentService.doPayment(order.getFinalAmount());
    }

    private Order createNewOrderFromCart() {
        Cart currentCart=customer.getCart();
        double cartTotal=currentCart.getTotal();
        double discountApplied= discountService.applyMaxDiscount(cartTotal);
        double finalAmount=cartTotal-discountApplied;
        Order order=new Order(currentCart.getShoppingCart(),finalAmount,customer);
        return order;
    }

    public void assignDeliveryPartner(Order order) {

        List<DeliveryPartner> partners = dpRepo.getDeliveryPartners();
        if (partners == null || partners.isEmpty()) {
            throw new NoDeliveryPartnerAvailableException();
        }

        Random random = new Random();
        DeliveryPartner partner = partners.get(random.nextInt(partners.size()));

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
            System.out.println(e.getClass().getSimpleName());
            System.out.println("Error in simulating delivery: " + e.getMessage());
        }

    }

    public void printInvoice(Order order) {

        Cart cart = customer.getCart();

        if (cart == null || cart.getShoppingCart().isEmpty()) {
            throw new EmptyCartException();
        }

        Map<FoodItem, OrderItem> items = cart.getShoppingCart();

        System.out.println("\n==================================================================");
        System.out.println("                             INVOICE                          ");
        System.out.println("==================================================================");
        System.out.println("Order ID       : " + order.getOrderId());
        System.out.println("Order Status   : " + order.getStatus());
        System.out.println("Delivery Agent : " +
                (order.getDeliveryPartner() != null
                        ? order.getDeliveryPartner().getName()
                        : "Not Assigned"));
        System.out.println("Delivery Address: " +
                (order.getCustomer().getCustomerAddress() != null
                        ? order.getCustomer().getCustomerAddress()
                        : "Not Provided"));
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

        double discount = discountService.applyMaxDiscount(cart.getTotal());
        double finalAmount = order.getFinalAmount();

        System.out.printf("%-48s %-12.2f%n", "Grand Total:", grandTotal);
        System.out.printf("%-48s %-12.2f%n", "Discount Applied:", discount);
        System.out.printf("%-48s %-12.2f%n", "Final Payable Amount:", finalAmount);

        System.out.println("==================================================================");
        System.out.println("              Thank You For Ordering With Us !!");
        System.out.println("==================================================================\n");
    }

    public void viewOrderHistory() {

        List<Order> history = customer.getOrderHistory();

        if (history == null || history.isEmpty()) {
            throw new NoOrdersFoundException();
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
