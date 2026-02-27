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
            System.out.println("===Delivery Partner DashBoard===");
            System.out.println("1. Show Order History");
            System.out.println("2. Show Total Earnings");
            System.out.println("3. Back");
            System.out.print("Enter your choice: ");
            int choice=validateInt();

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
                    System.out.println("Back to Main Menu...");
                    return;
                }
                default:{
                    System.out.println("Enter a valid choice !!");
                }
            }
        }


    }

    public void showOrderHistory(){
        List<Order> orders=dpRepo.getDeliveryPartnerOrders(user);
        if(orders.isEmpty()){
            System.out.println("No orders delivered yet.");
            return;
        }
        for(Order o:orders){
            System.out.println(o);
        }
    }

    public void showTotalEarnings(){
        double earning=0;
        List<Order> orders=dpRepo.getDeliveryPartnerOrders(user);
        for(Order o:orders){
            earning += deliveryPartnerCommission * o.getFinalAmount();
        }
        System.out.println("Total Earnings: "+earning);
    }


}
