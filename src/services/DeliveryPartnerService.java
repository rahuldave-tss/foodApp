package services;

import models.Order;
import models.User;
import repos.DPRepo;
import repos.UserRepo;

import static utils.Validate.*;

public class DeliveryPartnerService {
    private DPRepo dpRepo;

    public DeliveryPartnerService() {
        dpRepo=new DPRepo();
    }

    public void displayFeatures() throws InterruptedException {
        System.out.println("===Delivery Partner DashBoard===");
        System.out.println("1. Show Order History");
        System.out.println("2. Show Total Earnings");
        System.out.println("3. Exit");
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
                System.out.println("Exiting...");
                return;
            }
            default:{
                System.out.println("Enter a valid choice !!");
            }
        }

    }

    public void showOrderHistory(){
        User user=getPartnerInfo();
        if(user==null){
            System.out.println("No such Delivery Partner exists !!");
        }
        for(Order o:dpRepo.getDeliveryPartnerOrders(user)){
            System.out.println(o);
        }
    }

    private User getPartnerInfo() {
        System.out.print("Enter ID of partner: ");
        int id=validateInt();
        User user= new UserRepo().getUserById(id);
        return user;
    }

    public void showTotalEarnings(){
        User partner=getPartnerInfo();
        double earning=0;
        for(Order o:dpRepo.getDeliveryPartnerOrders(partner)){
            //10%
            earning+=(0.1*o.getCart().getTotal());
        }
        System.out.println("Total Earnings: "+earning);
    }


}
