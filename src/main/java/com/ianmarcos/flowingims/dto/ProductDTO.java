package com.ianmarcos.flowingims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
  private String name;
  private BrandDTO brand;
  private String description;
  private String sku;
  private String upc;
}
