package repos;

import models.AmountDiscount;
import models.DiscountStrategy;

import java.util.ArrayList;
import java.util.List;

public class DiscountRepo {
    private List<DiscountStrategy> availableDiscounts;

    public DiscountRepo(){
        availableDiscounts=new ArrayList<>();
        //initial discount
        this.addDiscount(new AmountDiscount("Amount Discount",500.0,10));
    }

    public List<DiscountStrategy> getAvailableDiscounts() {
        return availableDiscounts;
    }

    public void setAvailableDiscounts(List<DiscountStrategy> availableDiscounts) {
        this.availableDiscounts = availableDiscounts;
    }

    public void addDiscount(DiscountStrategy discount){
        availableDiscounts.add(discount);
    }

    public void removeDiscount(DiscountStrategy discount){
        availableDiscounts.remove(discount);
    }

    public DiscountStrategy findById(int id){
        return availableDiscounts.stream()
                .filter(d->d.getDiscountId()==id)
                .findFirst()
                .orElse(null);
    }

}
