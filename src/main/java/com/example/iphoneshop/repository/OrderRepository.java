package com.example.iphoneshop.repository;

import com.example.iphoneshop.entity.Order;
import com.example.iphoneshop.entity.enums.OrderStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> getOrdersByOrderStatusEquals(OrderStatus orderStatus);
}
