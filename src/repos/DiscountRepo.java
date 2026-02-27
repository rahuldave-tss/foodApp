package repos;

import models.AmountDiscount;
import models.IDiscount;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class DiscountRepo {
    private List<IDiscount> availableDiscounts;

    public DiscountRepo(){
        availableDiscounts=new ArrayList<>();
        //initial discount
        this.addDiscount(new AmountDiscount(500.0,10));
    }

    public List<IDiscount> getAvailableDiscounts() {
        return availableDiscounts;
    }

    public void setAvailableDiscounts(List<IDiscount> availableDiscounts) {
        this.availableDiscounts = availableDiscounts;
    }

    public void addDiscount(IDiscount discount){
        availableDiscounts.add(discount);
    }

    public void removeDiscount(IDiscount discount){
        availableDiscounts.remove(discount);
    }

}
