package com.example.iphoneshop.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IphoneDTO {
    private Long id;
    private String modelName;
    private Double price;
    private Integer quantity;
}
