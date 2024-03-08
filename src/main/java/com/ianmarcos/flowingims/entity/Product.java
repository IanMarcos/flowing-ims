package com.ianmarcos.flowingims.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity @Table
@Getter @Setter @ToString
@AllArgsConstructor
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_id")
  private int id;

  @Column
  private String name;

  @ManyToOne
  @JoinColumn(name="brand_id")
  private Brand brand;

  @Column
  private String description;

  @Column
  private String sku;

  @Column
  private String upc;

  @Column(name = "created_at")
  @CreationTimestamp
  private Date createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private Date updatedAt;

  @Column(columnDefinition = "boolean default true")
  private boolean enabled;

  public Product() {
    enabled = true;
  }
}
