package services;

import models.AmountDiscount;
import models.Cart;
import repos.DiscountRepo;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class AmountDiscountService implements IDiscountService {
    //for customer
    TreeMap<Double,Integer> amount_percentage;

    public AmountDiscountService() {
        this.amount_percentage = new TreeMap<>();
        initialDiscountsAdder();
    }

    private void initialDiscountsAdder() {
        amount_percentage.put(500.0,10);
    }

    @Override
    public double applyDiscount(Cart cart) {
        //can apply binary search after taking keySet from TreeMap instead of using linear search
        double discount=0;
        for(Map.Entry<Double,Integer> e:amount_percentage.entrySet()){
            if(e.getKey()<=cart.getTotal()){
                discount=Math.max(discount,(e.getValue()*cart.getTotal())/100);
            }
        }
        return discount;
    }

    @Override
    public String getDescription() {
        return "Amount Discount";
    }

    public TreeMap<Double, Integer> getAmount_percentage() {
        return amount_percentage;
    }
}
