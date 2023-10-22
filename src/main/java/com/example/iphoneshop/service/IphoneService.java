package com.example.iphoneshop.service;

import com.example.iphoneshop.dto.IphoneDTO;
import com.example.iphoneshop.entity.Iphone;
import com.example.iphoneshop.exception.IphoneNotFoundException;
import com.example.iphoneshop.repository.IphoneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Slf4j
@Service
public class IphoneService {

    @Autowired
    private IphoneRepository iphoneRepository;

    public Iphone getIphoneById(Long id) {
        return iphoneRepository.findById(id)
                .orElseThrow(() -> new IphoneNotFoundException("Dish cannot be found for id: " + id));

    }

    public List<Iphone> getAllIphones() {
        return iphoneRepository.findAll();
    }

    public Iphone createIphone(IphoneDTO iphoneDTO) {
        Iphone iphone = Iphone.builder()
                .id(iphoneDTO.getId())
                .modelName(iphoneDTO.getModelName())
                .price(iphoneDTO.getPrice())
                .quantity(iphoneDTO.getQuantity())
                .build();

        log.info("New iphone created, modelName: {}", iphone.getModelName());
        return iphoneRepository.save(iphone);
    }
}
