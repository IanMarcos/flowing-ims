package com.ianmarcos.flowingims.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity @Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Target {

  @Id
  @Column(name = "target_id")
  private int id;

  @Column
  @Size(min = 1, max = 10)
  @NotNull
  private String name;

  @Column(name = "short_name")
  @Size(min = 1, max = 3)
  @NotNull
  private String shortName;

  @Column(name = "created_at")
  @CreationTimestamp
  private Date createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private Date updatedAt;
}
