package com.felix180.shopping.service;

import com.felix180.shopping.entity.ProductCart;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ShoppingCartService {
    List<ProductCart> findProductCartByCodeCart(UUID code);

    List<ProductCart> addProductCart(UUID codeCart, ProductCart productCart);

    ProductCart update(UUID codeCart, ProductCart productCart);

    List<ProductCart> delete(UUID codeCart, UUID id);

    BigDecimal getCheckout(UUID id);

    UUID create();
}
