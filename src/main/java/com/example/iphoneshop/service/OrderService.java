package com.example.iphoneshop.service;

import com.example.iphoneshop.dto.OrderDTO;
import com.example.iphoneshop.entity.Iphone;
import com.example.iphoneshop.entity.Order;
import com.example.iphoneshop.entity.User;
import com.example.iphoneshop.entity.enums.OrderStatus;
import com.example.iphoneshop.exception.OrderNotFoundException;
import com.example.iphoneshop.repository.OrderRepository;
import com.example.iphoneshop.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Slf4j
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IphoneService iphoneService;

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order cannot be found for id: " + id));
    }

    public Order createOrder(OrderDTO orderDTO, Principal principal) {
        User user = getUserByPrincipal(principal);
        Order order = new Order();
        order.setOrderStatus(OrderStatus.NOT_PAID);
        order.setIphoneList(orderDTO.getIphoneList());
        order.setUser(user);

        log.info("Creating order for User: {}", user.getUsername());
        return orderRepository.save(order);
    }

    public Order addIphoneToOrder(Long orderId, Long iphoneId) {
        Order order = getOrderById(orderId);
        Iphone iphone = iphoneService.getIphoneById(iphoneId);
        order.getIphoneList().add(iphone);
        return order;
    }

    public Order removeIphoneFromOrder(Long orderId, Long iphoneId) {
        Order order = getOrderById(orderId);
        Iphone iphone = iphoneService.getIphoneById(iphoneId);
        order.getIphoneList().remove(iphone);
        return order;
    }

    public List<Order> getUnpaidOrders() {
        return orderRepository.getOrdersByOrderStatusEquals(OrderStatus.NOT_PAID);
    }

    public Order changeStatus(Long orderId) {
        Order order = getOrderById(orderId);
        order.setOrderStatus(OrderStatus.PAID);
        return orderRepository.save(order);
    }

    public void deleteOrders(List<Order> orders) {
        orderRepository.deleteAll(orders);
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found."));
    }
}
