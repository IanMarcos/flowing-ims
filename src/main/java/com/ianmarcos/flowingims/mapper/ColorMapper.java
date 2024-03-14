package com.ianmarcos.flowingims.mapper;

import com.ianmarcos.flowingims.dto.ObjectWithNameDTO;
import com.ianmarcos.flowingims.entity.Color;
import org.mapstruct.Mapper;

@Mapper
public interface ColorMapper {
  Color objectWithNameToColor(ObjectWithNameDTO objectWithNameDTO);
}
