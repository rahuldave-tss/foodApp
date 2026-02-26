package repos;

import models.Order;
import models.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class DPRepo {
    //partner - list of orders
    private HashMap<User,List<Order>> deliveryPartners;

    public DPRepo(){
        deliveryPartners=new HashMap<>();
    }

    public List<User> getDeliveryPartners() {
        return new ArrayList<>(deliveryPartners.keySet());
    }

    public List<Order> getDeliveryPartnerOrders(User deliveryPartner){
        return deliveryPartners.get(deliveryPartner);
    }

    public void addPartner(User partner){
        deliveryPartners.put(partner,new ArrayList<>());
    }

    public void removePartner(User partner){
        deliveryPartners.remove(partner);
    }
}
