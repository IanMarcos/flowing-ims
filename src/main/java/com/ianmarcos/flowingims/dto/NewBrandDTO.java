package com.ianmarcos.flowingims.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewBrandDTO {
  @Size(min = 3, max = 50)
  @NotNull
  private String name;
}
