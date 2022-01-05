package com.felix180.shopping.service;

import com.felix180.shopping.data.ProductDataTest;
import com.felix180.shopping.entity.ShoppingCart;
import com.felix180.shopping.repository.ProductCartRepository;
import com.felix180.shopping.repository.ShoppingCartRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.UUID;

@SpringBootTest
class ShoppingCartServiceImplTest {

  public static final UUID UUID_CART = UUID.fromString("716fd6ed-93e1-4329-8878-e549ca2d784d");
  @MockBean ProductCartRepository ProductCartRepository;

  @MockBean ShoppingCartRepository shoppingCartRepository;

  @Autowired ShoppingCartService shoppingCartService;

  @BeforeEach
  void setUp() {
    Mockito.when(shoppingCartRepository.findById(UUID_CART))
        .thenReturn(java.util.Optional.of(ProductDataTest.getShoppingCart()));

    Mockito.when(ProductCartRepository.save(ArgumentMatchers.any()))
        .thenAnswer(
            (Answer<ShoppingCart>)
                invocationOnMock -> {
                  Object[] args = invocationOnMock.getArguments();
                  return (ShoppingCart) args[0];
                })
        .thenReturn(ProductDataTest.getShoppingCart());
  }

  @Test
  void getCheckout_18() {
    BigDecimal expected = BigDecimal.valueOf(18);
    BigDecimal result = shoppingCartService.getCheckout(UUID_CART);
    Assertions.assertEquals(expected, result);
  }
}
