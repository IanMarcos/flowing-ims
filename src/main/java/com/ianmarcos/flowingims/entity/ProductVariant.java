package com.ianmarcos.flowingims.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "product_variant")
@Data @AllArgsConstructor
public class ProductVariant {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_variant_id")
  private int id;

  @ManyToOne
  @JoinColumn(name = "product_id")
  @NotNull
  private Product product;

  @ManyToOne
  @JoinColumn(name = "target_id")
  @NotNull
  private Target target;

  @ManyToOne
  @JoinColumn(name = "size_id")
  @NotNull
  private Size size;

  @ManyToOne
  @JoinColumn(name = "color_id")
  @NotNull
  private Color color;

  @Column
  @jakarta.validation.constraints.Size(min = 1, max = 255)
  private String materials;

  @Column(name = "created_at")
  @CreationTimestamp
  private Date createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private Date updatedAt;

  @Column
  private boolean enabled;

  public ProductVariant() {
    this.enabled = true;
  }
}
