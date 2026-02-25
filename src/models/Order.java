package models;

import java.time.LocalDate;
import java.util.Random;

public class Order {
    private int id;
    private LocalDate orderDate;
    private OrderStatus orderStatus;
    private Cart cart;

    public Order() {
        this.id = randomIdGenerator();
        this.orderDate = LocalDate.now();
        this.orderStatus = OrderStatus.PREPARING;
        this.cart=new Cart();
    }

    private int randomIdGenerator() {
        Random random=new Random();
        //check if random is not repeated
        return random.nextInt(1,10000);
    }

    public int getId() {
        return id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Cart getCart() {
        return cart;
    }

    //tabular format
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", orderStatus=" + orderStatus +
                ", cart=" + cart +
                '}';
    }
}
