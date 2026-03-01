package controllers;

import models.DeliveryPartner;
import models.User;
import services.DeliveryPartnerService;

import static utils.Validate.validateInt;

public class DeliveryPartnerController {
    private DeliveryPartnerService deliveryPartnerService;
    private DeliveryPartner deliveryPartner;

    public DeliveryPartnerController(DeliveryPartnerService deliveryPartnerService, User deliveryPartner) {
        this.deliveryPartnerService = deliveryPartnerService;
        this.deliveryPartner=(DeliveryPartner) deliveryPartner;
    }

    public void start(){
        while(true){

            System.out.println("\n================================================");
            System.out.println("            DELIVERY PARTNER DASHBOARD          ");
            System.out.println("================================================");
            System.out.println(" Please select an option:");
            System.out.println("------------------------------------------------");
            System.out.println("  1. Show Order History");
            System.out.println("  2. Show Total Earnings");
            System.out.println("  3. Confirm Current Order");
            System.out.println("  4. Logout");
            System.out.println("------------------------------------------------");
            System.out.print(" Enter your choice (1-3): ");

            int choice = validateInt();
            System.out.println();

            switch (choice){

                case 1:{
                    deliveryPartnerService.showOrderHistory();
                    break;
                }

                case 2:{
                    deliveryPartnerService.showTotalEarnings();
                    break;
                }
                case 3:{
                    deliveryPartnerService.confirmOrder(deliveryPartner);
                    break;
                }
                case 4:{
                    System.out.println("Logging out...");
                    System.out.println("================================================\n");
                    return;
                }

                default:{
                    System.out.println("Invalid choice! Please enter a number between 1 and 3.");
                }
            }
        }
    }


}
