package com.felix180.shopping.repository;

import com.felix180.shopping.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, UUID> {}
