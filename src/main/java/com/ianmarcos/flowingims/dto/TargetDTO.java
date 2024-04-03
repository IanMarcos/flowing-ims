package com.ianmarcos.flowingims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TargetDTO {
  private int id;
  private String name;
  private String shortName;
}
