package com.example.iphoneshop.controller;

import com.example.iphoneshop.dto.OrderDTO;
import com.example.iphoneshop.entity.Order;
import com.example.iphoneshop.mapper.OrderMapper;
import com.example.iphoneshop.service.OrderService;
import com.example.iphoneshop.validation.ResponseErrorValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ResponseErrorValidation responseErrorValidation;

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable String orderId) {
        Order order = orderService.getOrderById(Long.parseLong(orderId));
        OrderDTO orderDTO = orderMapper.orderToOrderDTO(order);
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Object> createOrder(@Valid @RequestBody OrderDTO orderDTO,
                                              BindingResult bindingResult,
                                              Principal principal) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) {
            return errors;
        }

        Order order = orderService.createOrder(orderDTO, principal);
        OrderDTO createdOrder = orderMapper.orderToOrderDTO(order);
        return ResponseEntity.ok(createdOrder);
    }

    @PostMapping("/{orderId}/{iphoneId}")
    public ResponseEntity<OrderDTO> addIphoneToOrder(@PathVariable String orderId,
                                                 @PathVariable String iphoneId) {
        Order order = orderService.addIphoneToOrder(Long.parseLong(orderId), Long.parseLong(iphoneId));
        OrderDTO orderDTO = orderMapper.orderToOrderDTO(order);
        return ResponseEntity.ok(orderDTO);
    }

    @DeleteMapping("/{orderId}/{iphoneId}")
    public ResponseEntity<Object> removeDishFromOrder(@PathVariable String orderId,
                                                      @PathVariable String iphoneId) {
        Order order = orderService.removeIphoneFromOrder(Long.parseLong(orderId), Long.parseLong(iphoneId));
        return new ResponseEntity<>(order, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/unpaid")
    public ResponseEntity<List<OrderDTO>> getUnpaidOrders() {
        List<OrderDTO> orderDTOList = orderService.getUnpaidOrders()
                .stream()
                .map(orderMapper::orderToOrderDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderDTOList);
    }

    @PostMapping("/manager/{orderId}/")
    public ResponseEntity<OrderDTO> changeOrderStatus(@PathVariable String orderId) {
        Order order = orderService.changeStatus(Long.parseLong(orderId));
        OrderDTO orderDTO = orderMapper.orderToOrderDTO(order);
        return ResponseEntity.ok(orderDTO);
    }
}
