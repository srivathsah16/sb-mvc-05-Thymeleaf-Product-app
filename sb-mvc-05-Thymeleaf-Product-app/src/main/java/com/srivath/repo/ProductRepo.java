package com.srivath.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srivath.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
