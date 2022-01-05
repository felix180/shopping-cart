package com.felix180.shopping.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
public class ProductCart {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  @ManyToOne(fetch = FetchType.EAGER) private ShoppingCart carro;

  @NotNull
  @OneToOne(fetch = FetchType.EAGER) private Product producto;

  @NotNull
  private Integer cantidad;
}
