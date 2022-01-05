package com.felix180.shopping.data;

import com.felix180.shopping.entity.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductDataTest {

  public static List<Product> getProductList() {
    List<Product> products = new ArrayList<>();
    products.add(getProductLlave());
    products.add(getProductMartillo());
    return products;
  }

  public static Product getProductLlave() {
    Product product =
        new Product(
            UUID.fromString("716fd6ed-93e1-4329-8878-e549ca2d784d"),
            "Llave",
            "0001",
            "Descripcion",
            BigDecimal.valueOf(6L),
            TypeProduct.SIMPLE);
    return product;
  }

  public static Product getProductMartillo() {
    Product product =
        new Product(
            UUID.fromString("716fd6ed-93e1-4329-8878-e549ca2d784d"),
            "Martillo",
            "0002",
            "Descripcion",
            BigDecimal.valueOf(4L),
            TypeProduct.DESCUENTO);
    return product;
  }

  public static ProductCart getProductCartLlave_2() {
    ProductCart productCart = new ProductCart();
    productCart.setProducto(getProductLlave());
    productCart.setCantidad(2);
    return productCart;
  }

  public static ProductCart getProductCartMartillo_3() {
    ProductCart productCart = new ProductCart();
    productCart.setProducto(getProductMartillo());
    productCart.setCantidad(3);
    return productCart;
  }

  public static List<ProductCart> getListProductCart() {
    List<ProductCart> productCarts = new ArrayList<>();
    productCarts.add(getProductCartLlave_2());
    productCarts.add(getProductCartMartillo_3());
    return productCarts;
  }

  public static ShoppingCart getShoppingCart() {
    ShoppingCart shoppingCart = new ShoppingCart();
    shoppingCart.setList(getListProductCart());
    shoppingCart.setEstado(StatusCart.PENDIENTE);
    shoppingCart.setId(UUID.fromString("716fd6ed-93e1-4329-8878-e549ca2d784d"));
    return shoppingCart;
  }
}
