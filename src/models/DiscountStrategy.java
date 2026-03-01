package models;

public interface DiscountStrategy {
    int getDiscountId();
    void setDiscountPercentage(double discountPercentage);
    double getDiscountPercentage();
    double getAmount();
}
