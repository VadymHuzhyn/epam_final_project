package ua.nure.huzhyn.services;

import ua.nure.huzhyn.db.dao.dto.OrderDto;
import ua.nure.huzhyn.model.entity.Order;
import ua.nure.huzhyn.model.entity.enums.OrderStatus;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrderList();

    Order getOrderById(String orderId);

    boolean updateOrderStatus(String orderId, OrderStatus status);

    List<Order> getOrderByUserId(String userId);

    void addOrder(Order order, String routsId);
}
