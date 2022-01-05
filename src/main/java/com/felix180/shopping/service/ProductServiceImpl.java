package com.felix180.shopping.service;

import com.felix180.shopping.entity.Product;
import com.felix180.shopping.exception.NoSuchElementFoundException;
import com.felix180.shopping.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
  private ProductRepository repository;

  @Override
  public List<Product> getAll() {
    return repository.findAll();
  }

  @Override
  public Product findById(UUID id) {
    Optional<Product> byId = repository.findById(id);
    return byId.orElseThrow(new NoSuchElementFoundException("Not found id " + id));
  }

  @Override
  public Product create(Product product) {
    return repository.save(product);
  }

  @Override
  public Product update(Product product) {
    Product productToUpdate = repository.getById(product.getId());
    return repository.save(productToUpdate);
  }

  @Override
  public Boolean delete(UUID id) {

    repository.deleteById(id);
    return true;
  }
}
