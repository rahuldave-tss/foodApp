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

        System.out.println("\n------------------------------------------------");
        System.out.println("                ORDER HISTORY                   ");
        System.out.println("------------------------------------------------");

        List<Order> orders = dpRepo.getDeliveryPartnerOrders(deliveryPartner);

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
        List<Order> orders = dpRepo.getDeliveryPartnerOrders(deliveryPartner);

        for(Order o : orders){
            earning += deliveryPartnerCommission * o.getFinalAmount();
        }

        System.out.println(" Total Earnings: " + earning);
        System.out.println("------------------------------------------------\n");
    }


}
