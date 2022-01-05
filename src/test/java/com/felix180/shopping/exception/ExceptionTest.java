package com.felix180.shopping.exception;

import com.felix180.shopping.controller.ProductController;
import com.felix180.shopping.repository.ProductRepository;
import com.felix180.shopping.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({ProductController.class, ProductService.class})
class ExceptionTest {

  public static final String ID_PRODUCT = "716fd6ed-93e1-4329-8878-e549ca2d784d";
  @Autowired private MockMvc mvc;

  @MockBean private ProductRepository productRepository;

  @Test
  void getFindErrorMessageNotFound() throws Exception {
    Mockito.when(productRepository.findById(UUID.fromString(ID_PRODUCT)))
        .thenReturn(Optional.empty());

    mvc.perform(get("/findById/" + ID_PRODUCT).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }
}
