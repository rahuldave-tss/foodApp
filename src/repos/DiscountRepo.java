package repos;

import models.IDiscount;

import java.util.ArrayList;
import java.util.List;

public class DiscountRepo {
    private List<IDiscount> availableDiscounts;

    public DiscountRepo(){
        availableDiscounts=new ArrayList<>();
    }

    public List<IDiscount> getAvailableDiscounts() {
        return availableDiscounts;
    }

    public void addDiscount(IDiscount discount){
        availableDiscounts.add(discount);
    }

    public void removeDiscount(IDiscount discount){
        availableDiscounts.remove(discount);
    }

}
