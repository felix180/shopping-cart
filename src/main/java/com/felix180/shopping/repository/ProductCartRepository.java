package com.felix180.shopping.repository;

import com.felix180.shopping.entity.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductCartRepository extends JpaRepository<ProductCart, UUID> {}
