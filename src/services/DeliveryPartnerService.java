package services;

import models.Order;
import models.User;
import repos.DPRepo;
import repos.UserRepo;

import java.util.List;

import static utils.GlobalConstants.deliveryPartnerCommission;
import static utils.Validate.*;

public class DeliveryPartnerService {
    private DPRepo dpRepo;
    private User user;

    public DeliveryPartnerService(DPRepo dpRepo,User loggedInUser) {
        this.dpRepo=dpRepo;
        this.user=loggedInUser;
    }

    public void displayFeatures()  {

        while(true){

            System.out.println("\n================================================");
            System.out.println("            DELIVERY PARTNER DASHBOARD          ");
            System.out.println("================================================");
            System.out.println(" Please select an option:");
            System.out.println("------------------------------------------------");
            System.out.println("  1. Show Order History");
            System.out.println("  2. Show Total Earnings");
            System.out.println("  3. Back to Main Menu");
            System.out.println("------------------------------------------------");
            System.out.print(" Enter your choice (1-3): ");

            int choice = validateInt();
            System.out.println();

            switch (choice){

                case 1:{
                    showOrderHistory();
                    break;
                }

                case 2:{
                    showTotalEarnings();
                    break;
                }

                case 3:{
                    System.out.println(" Returning to Main Menu...");
                    System.out.println("================================================\n");
                    return;
                }

                default:{
                    System.out.println(" Invalid choice! Please enter a number between 1 and 3.");
                }
            }
        }
    }

    public void showOrderHistory(){

        System.out.println("\n------------------------------------------------");
        System.out.println("                ORDER HISTORY                   ");
        System.out.println("------------------------------------------------");

        List<Order> orders = dpRepo.getDeliveryPartnerOrders(user);

        if(orders.isEmpty()){
            System.out.println(" No orders delivered yet.");
            System.out.println("------------------------------------------------\n");
            return;
        }

        for(Order o : orders){
            System.out.println(o);
        }

        System.out.println("------------------------------------------------\n");
    }

    public void showTotalEarnings(){

        System.out.println("\n------------------------------------------------");
        System.out.println("                TOTAL EARNINGS                  ");
        System.out.println("------------------------------------------------");

        double earning = 0;
        List<Order> orders = dpRepo.getDeliveryPartnerOrders(user);

        for(Order o : orders){
            earning += deliveryPartnerCommission * o.getFinalAmount();
        }

        System.out.println(" Total Earnings: " + earning);
        System.out.println("------------------------------------------------\n");
    }


}
