package com.example.iphoneshop.job;

import com.example.iphoneshop.entity.Order;
import com.example.iphoneshop.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class DeleteOldOrders {

    @Autowired
    private OrderService orderService;

    @Scheduled(fixedDelayString = "${job.deleteOldOrderFixedRate}")
    public void deleteOldOrders() {
        List<Order> orderList = orderService.getUnpaidOrders();
        List<Order> oldOrderList = new ArrayList<>();
        for (Order order : orderList) {
            if (order.getCreatedDate().isBefore(LocalDateTime.now().minusMinutes(10))) {
                oldOrderList.add(order);
            }
        }
        if (!CollectionUtils.isEmpty(oldOrderList)) {
            log.info("Deleting orders: {}", oldOrderList.stream()
                    .map(Order::getId)
                    .collect(Collectors.toList()));
            orderService.deleteOrders(oldOrderList);
        }
    }
}
