package services;

import models.DeliveryPartner;
import models.Order;
import models.User;
import repos.DPRepo;
import repos.UserRepo;

import java.util.List;

import static utils.GlobalConstants.deliveryPartnerCommission;
import static utils.Validate.*;

public class DeliveryPartnerService {
    private DPRepo dpRepo;
    private DeliveryPartner deliveryPartner;

    public DeliveryPartnerService(DPRepo dpRepo,User deliveryPartner) {
        this.dpRepo=dpRepo;
        this.deliveryPartner=(DeliveryPartner) deliveryPartner;
    }

    public void showOrderHistory(){
        List<Order> orders = dpRepo.getDeliveryPartnerOrders(deliveryPartner);
        if(orders.isEmpty()){
            System.out.println("No orders delivered yet.");
            return;
        }

        System.out.println("\n------------------------------------------------");
        System.out.println("                ORDER HISTORY                   ");
        System.out.println("------------------------------------------------");
        System.out.println(displayOrderHistoryHeader());
        for(Order o : orders){
            System.out.println(o.getDeliveryPartnerHistoryRow());
        }
        System.out.println("------------------------------------------------\n");
    }

    public static String displayOrderHistoryHeader() {
        return "+----------+--------------+-----------------+--------------------+--------------------------------+\n" +
                "| Order ID | Final Amount | Status          | Customer           | Items                          |\n" +
                "+----------+--------------+-----------------+--------------------+--------------------------------+";
    }

    public void showTotalEarnings(){
        double earning = 0;
        List<Order> orders = dpRepo.getDeliveryPartnerOrders(deliveryPartner);
        if(orders.isEmpty()){
            System.out.println("No orders delivered yet. Total earnings: "+earning);
            return;
        }

        System.out.println("\n------------------------------------------------");
        System.out.println("                TOTAL EARNINGS                  ");
        System.out.println("------------------------------------------------");
        for(Order o : orders){
            earning += deliveryPartnerCommission * o.getFinalAmount();
        }
        System.out.println(" Total Earnings: " + earning);
        System.out.println("------------------------------------------------\n");
    }


}
