package notifications;

import models.Order;

public interface Observer {
    void update(Order order);
}
