package com.example.iphoneshop.repository;

import com.example.iphoneshop.entity.Iphone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IphoneRepository extends CrudRepository<Iphone, Long> {
    List<Iphone> findAll();
}
