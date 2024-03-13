package com.ianmarcos.flowingims.dto;

import com.ianmarcos.flowingims.validation.ProductIdentification;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ProductIdentification
public class NewBaseProductDTO {
  @NotNull
  @Size(min = 3, max = 200)
  private String name;
  private int brandId;
  @Size(max = 1000)
  private String description;
  @Size(min = 6, max = 40)
  private String sku;
  @Size(min = 12, max = 14)
  private String upc;
}
