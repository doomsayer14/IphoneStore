package com.example.iphoneshop.mapper;

import com.example.iphoneshop.dto.IphoneDTO;
import com.example.iphoneshop.entity.Iphone;
import org.springframework.stereotype.Component;

@Component
public class IphoneMapper {
    public IphoneDTO iphoneToIphoneDto(Iphone iphone) {
        return IphoneDTO.builder()
                .id(iphone.getId())
                .modelName(iphone.getModelName())
                .price(iphone.getPrice())
                .build();
    }
}