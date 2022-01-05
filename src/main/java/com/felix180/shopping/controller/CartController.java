package com.felix180.shopping.controller;

import com.felix180.shopping.entity.ProductCart;
import com.felix180.shopping.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController("carro")
@Api(value = "carro")
@RequestMapping("/carro")
@AllArgsConstructor
public class CartController {
  private ShoppingCartService shoppingCartService;

  @GetMapping("/new")
  public UUID create() {
    return shoppingCartService.create();
  }

  @ApiOperation(value = "View a list of available ProducstCarts", response = List.class)
  @GetMapping("/{codeCart}")
  public List<ProductCart> getAll(@RequestParam("codeCart") UUID code) {
    return shoppingCartService.findProductCartByCodeCart(code);
  }

  @PostMapping("/{codeCart}")
  public List<ProductCart> addProductCart(
      @PathVariable("codeCart") UUID codeCart,@Validated @RequestBody ProductCart productCart) {
    return shoppingCartService.addProductCart(codeCart, productCart);
  }

  @ApiOperation(value = "update ProductCarts by body", response = ProductCart.class)
  @PutMapping("/update")
  public ProductCart update(
      @RequestParam("codeCart") UUID codeCart,@Validated @RequestBody ProductCart productCart) {
    return shoppingCartService.update(codeCart, productCart);
  }

  @ApiOperation(value = "delate ProductCart by id", response = ProductCart.class)
  @DeleteMapping("{codeCart}/removeProduct/{idProductCart}")
  public List<ProductCart> delete(
      @PathVariable("codeCart") UUID codeCart, @PathVariable("idProductCart") UUID idProductCart) {
    return shoppingCartService.delete(codeCart, idProductCart);
  }

  @PostMapping("/{codeCart}/checkout")
  public BigDecimal getCheckout(@PathVariable("codeCart") UUID id) {

    return shoppingCartService.getCheckout(id);
  }
}
