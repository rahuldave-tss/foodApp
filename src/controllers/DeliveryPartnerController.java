package controllers;

import services.DeliveryPartnerService;

import static utils.Validate.validateInt;

public class DeliveryPartnerController {
    private DeliveryPartnerService deliveryPartnerService;

    public DeliveryPartnerController(DeliveryPartnerService deliveryPartnerService) {
        this.deliveryPartnerService = deliveryPartnerService;
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
            System.out.println("  3. Back to Main Menu");
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


}
