package com.felix180.shopping.controller;

import com.felix180.shopping.data.ProductDataTest;
import com.felix180.shopping.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

  @Autowired private MockMvc mvc;

  @MockBean private ProductService service;

  @Test
  void getAll() throws Exception {
    Mockito.when(service.getAll()).thenReturn(ProductDataTest.getProductList());

    mvc.perform(get("/producto/getAll").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect( jsonPath("$[0].nombre", is(ProductDataTest.getProductLlave().getNombre())));
  }
}
