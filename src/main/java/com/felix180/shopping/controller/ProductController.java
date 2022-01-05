package com.felix180.shopping.controller;

import com.felix180.shopping.entity.Product;
import com.felix180.shopping.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController("producto")
@Api(value = "producto")
@RequestMapping("/producto")
@AllArgsConstructor
public class ProductController {

  private ProductService productService;

  @ApiOperation(value = "View a list of available Producs", response = List.class)
  @GetMapping("/getAll")
  public List<Product> getAll() {
    return productService.getAll();
  }

  @ApiOperation(value = "find Producs by id", response = Product.class)
  @GetMapping("/findById/{id}")
  public Product findById(@PathVariable("id") UUID id) {
    return productService.findById(id);
  }

  @PostMapping("/create")
  public Product create(@Validated @RequestBody Product product) {
    return productService.create(product);
  }

  @ApiOperation(value = "update Produc by body", response = Product.class)
  @PutMapping("/update")
  public Product update(@Validated @RequestBody Product product) {
    return productService.update(product);
  }

  @ApiOperation(value = "delate Produc by id", response = Product.class)
  @DeleteMapping("/delete/{id}")
  public Boolean delete(@PathVariable("id") UUID id) {
    return productService.delete(id);
  }

  @ApiIgnore
  @RequestMapping(value = "/")
  public void redirect(HttpServletResponse response) throws IOException {
    response.sendRedirect("/swagger-ui.html");
  }
}
