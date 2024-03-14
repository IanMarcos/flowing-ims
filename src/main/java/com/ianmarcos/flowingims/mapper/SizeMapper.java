package com.ianmarcos.flowingims.mapper;

import com.ianmarcos.flowingims.dto.ObjectWithNameDTO;
import com.ianmarcos.flowingims.entity.Size;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SizeMapper {
  @Mapping(source = "name", target = "value")
  Size objectWithNameToSize(ObjectWithNameDTO objectWithNameDTO);
}
