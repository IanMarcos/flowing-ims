package com.ianmarcos.flowingims.mapper;

import com.ianmarcos.flowingims.dto.BrandDTO;
import com.ianmarcos.flowingims.dto.ObjectWithNameDTO;
import com.ianmarcos.flowingims.entity.Brand;
import org.mapstruct.Mapper;

@Mapper
public interface BrandMapper {
  Brand objectWithNameToBrand(ObjectWithNameDTO objectWithNameDTO);
  Brand brandDtoToBrand(BrandDTO brandDTO);
}
