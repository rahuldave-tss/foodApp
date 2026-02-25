package services;

import models.Cart;

public interface IDiscountService{
    double applyDiscount(Cart cart);
    String getDescription();
}
