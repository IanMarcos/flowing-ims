package com.ianmarcos.flowingims.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity @Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Size {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "size_id")
  private int id;

  @Column
  @jakarta.validation.constraints.Size(min = 1, max = 7)
  @NotNull
  private String value;

  @Column(name = "created_at")
  @CreationTimestamp
  private Date createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private Date updatedAt;
}

