package com.example.iphoneshop.mapper;

import com.example.iphoneshop.dto.OrderDTO;
import com.example.iphoneshop.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderDTO orderToOrderDTO(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .iphoneList(order.getIphoneList())
                .username(order.getUser().getUsername())
                .orderStatus(order.getOrderStatus())
                .createdDate(order.getCreatedDate())
                .build();
    }
}
