package com.ianmarcos.flowingims.entity;

import com.ianmarcos.flowingims.validation.ProductIdentification;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity @Table
@Getter @Setter @ToString
@AllArgsConstructor
@ProductIdentification
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_id")
  private int id;

  @Column
  @NotNull
  @Size(min = 3, max = 200)
  private String name;

  @ManyToOne
  @JoinColumn(name="brand_id")
  private Brand brand;

  @Column
  @Size(max = 1000)
  private String description;

  @Column
  @Size(min = 6, max = 40)
  private String sku;

  @Column
  @Size(min = 12, max = 14)
  private String upc;

  @Column(name = "created_at")
  @CreationTimestamp
  private Date createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private Date updatedAt;

  @Column
  private boolean enabled;

  public Product() {
    enabled = true;
  }
}
