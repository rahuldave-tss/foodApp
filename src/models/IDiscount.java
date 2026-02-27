package models;

public interface IDiscount {
    int getId();
    double getAmount();
    double getDiscountPercentage();
    void setDiscountPercentage(double discountPercentage);
}
