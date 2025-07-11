package com.sangwon.springbootplayground.repository;

import com.sangwon.springbootplayground.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
