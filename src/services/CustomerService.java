package services;

import models.Cart;
import models.Menu;
import models.OrderItem;
import models.User;
import repos.DPRepo;

import java.util.List;
import java.util.Random;

import static utils.Validate.validateInt;

public class CustomerService {
    private MenuService menuService;
    private OrderService orderService;
    private DPRepo dpRepo;

    public CustomerService() {
        this.menuService = new MenuService();
        this.orderService = new OrderService();
        this.dpRepo=new DPRepo();
    }

    public void displayFeatures() throws InterruptedException {
        System.out.println("===Customer DashBoard===");
        System.out.println("1. Add Items to cart");
        System.out.println("2. Remove Items to cart");
        System.out.println("3. View Cart Summary");
        System.out.println("4. Place Order");
        System.out.println("5. Exit");
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
                orderService.confirmOrder();
                assignDeliveryPartner();
                break;
            }
            case 5:{
                System.out.println("Exiting...");
                return;
            }
            default:{
                System.out.println("Enter a valid choice !!");
            }
        }

    }

    private void displayMenu(){
        menuService.displayMenu();
    }

    public void assignDeliveryPartner() throws InterruptedException {
        List<User> availableDeliveryPartners=dpRepo.getDeliveryPartners();
        Random random=new Random();
        User deliveryPartner=availableDeliveryPartners.get(random.nextInt(availableDeliveryPartners.size()));
        System.out.println("Food is being prepared...");
        Thread.sleep(2000);
        System.out.println(deliveryPartner.getName()+" is assigned as Delivery Partner...");
        Thread.sleep(2000);
        System.out.println("Food is on the way....");
        Thread.sleep(2000);
        System.out.println("Order Delivered !!!");
    }

    public void printInvoice(){
        //print payment invoice
    }

}
