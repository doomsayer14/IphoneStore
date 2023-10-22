package com.example.iphoneshop.dto;

import com.example.iphoneshop.entity.Iphone;
import com.example.iphoneshop.entity.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderDTO {
    private Long id;
    private List<Iphone> iphoneList;
    private String username;
    private OrderStatus orderStatus;
    private LocalDateTime createdDate;
}
