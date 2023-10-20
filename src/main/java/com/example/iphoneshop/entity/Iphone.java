package com.example.iphoneshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Iphone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String modelName;

    @Column
    private Double price;

    @Column
    private Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    private Order order;
}
