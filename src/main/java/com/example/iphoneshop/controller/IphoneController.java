package com.example.iphoneshop.controller;

import com.example.iphoneshop.dto.IphoneDTO;
import com.example.iphoneshop.entity.Iphone;
import com.example.iphoneshop.mapper.IphoneMapper;
import com.example.iphoneshop.service.IphoneService;
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
@RequestMapping("/api/iphone")
public class IphoneController {

    @Autowired
    private IphoneService iphoneService;

    @Autowired
    private IphoneMapper iphoneMapper;

    @Autowired
    private ResponseErrorValidation responseErrorValidation;

    @PostMapping("/manager/create")
    public ResponseEntity<Object> createIphone(@Valid @RequestBody IphoneDTO iphoneDTO,
                                             BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        Iphone iphone = iphoneService.createIphone(iphoneDTO);
        IphoneDTO createdIphone = iphoneMapper.iphoneToIphoneDto(iphone);

        return new ResponseEntity<>(createdIphone, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<IphoneDTO>> getAllIphones() {
        List<IphoneDTO> iphoneDTOList = iphoneService.getAllIphones()
                .stream()
                .map(iphoneMapper::iphoneToIphoneDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(iphoneDTOList);
    }
}
