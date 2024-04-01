package com.ianmarcos.flowingims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariantDTO {
  private int id;
  private ProductDTO product;
  private SizeDTO size;
  private ColorDTO color;
  private TargetDTO target;
  private String materials;
}
