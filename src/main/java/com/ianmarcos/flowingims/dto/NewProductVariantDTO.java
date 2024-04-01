package com.ianmarcos.flowingims.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewProductVariantDTO {
  @NotNull
  private int productId;
  @NotNull
  private String target;
  @NotNull
  private int sizeId;
  @NotNull
  private int colorId;
  @jakarta.validation.constraints.Size(min = 1, max = 255)
  private String materials;

}
