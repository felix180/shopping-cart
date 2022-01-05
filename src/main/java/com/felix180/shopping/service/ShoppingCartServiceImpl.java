package com.felix180.shopping.service;

import com.felix180.shopping.entity.*;
import com.felix180.shopping.exception.NoSuchElementFoundException;
import com.felix180.shopping.repository.ProductCartRepository;
import com.felix180.shopping.repository.ProductRepository;
import com.felix180.shopping.repository.ShoppingCartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
  private ShoppingCartRepository shoppingCartRepository;
  private ProductCartRepository productCartRepository;
  private ProductRepository productRepository;

  @Override
  public List<ProductCart> findProductCartByCodeCart(UUID code) {
    return shoppingCartRepository.findById(code).orElse(new ShoppingCart()).getList();
  }

  @Override
  public List<ProductCart> addProductCart(UUID codeCart, ProductCart productCart) {
    Optional<ShoppingCart> byId = shoppingCartRepository.findById(codeCart);
    Product productById = productRepository.getById(productCart.getProducto().getId());
    ShoppingCart shoppingCart = byId.orElse(new ShoppingCart());
    productCart.setCarro(shoppingCart);
    productCart.setProducto(productById);

    ProductCart productCartSave = productCartRepository.save(productCart);
    shoppingCart.getList().add(productCartSave);
    return shoppingCart.getList();
  }

  @Override
  public ProductCart update(UUID codeCart, ProductCart productCart) {
    Optional<ShoppingCart> byId = shoppingCartRepository.findById(codeCart);
    ShoppingCart shoppingCart = byId.orElse(new ShoppingCart());

    productCart.setCarro(shoppingCart);
    ProductCart save = productCartRepository.save(productCart);
    return save;
  }

  @Override
  public List<ProductCart> delete(UUID codeCart, UUID id) {
    productCartRepository.deleteById(id);
    return shoppingCartRepository.findById(codeCart).orElse(new ShoppingCart()).getList();
  }

  @Override
  public BigDecimal getCheckout(UUID id) {
    ShoppingCart shoppingCart =
        shoppingCartRepository
            .findById(id)
            .orElseThrow(new NoSuchElementFoundException("Not found id " + id));

    long sum = getSumProductCarts(shoppingCart);

    shoppingCart.setEstado(StatusCart.COMPLETADO);
    shoppingCartRepository.save(shoppingCart);
    return new BigDecimal(sum);
  }

  private long getSumProductCarts(ShoppingCart shoppingCart) {
    return shoppingCart.getList().stream()
        .map(
            productCart -> {
              Product product = productCart.getProducto();
              BigDecimal cantidad = BigDecimal.valueOf(productCart.getCantidad());
              if (TypeProduct.SIMPLE.equals(product.getTypeProduct())) {
                return product.getPrecio().multiply(cantidad);
              }
              return product
                  .getPrecio()
                  .multiply(cantidad)
                  .divide(new BigDecimal(2), RoundingMode.HALF_DOWN);
            })
        .mapToLong(BigDecimal::longValue)
        .sum();
  }

  @Override
  public UUID create() {
    ShoppingCart shoppingCart = new ShoppingCart();
    shoppingCart.setList(new ArrayList<>());
    shoppingCart.setEstado(StatusCart.PENDIENTE);
    ShoppingCart save = shoppingCartRepository.save(shoppingCart);
    return save.getId();
  }
}
