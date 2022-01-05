package com.felix180.shopping.service;

import com.felix180.shopping.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

  List<Product> getAll();

  Product findById(UUID id);

  Product create(Product heroe);

  Product update(Product heroe);

  Boolean delete(UUID id);
}
