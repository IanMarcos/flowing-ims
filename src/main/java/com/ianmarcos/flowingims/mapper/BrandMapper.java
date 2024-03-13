package com.ianmarcos.flowingims.mapper;

import com.ianmarcos.flowingims.dto.BrandDTO;
import com.ianmarcos.flowingims.dto.NewBrandDTO;
import com.ianmarcos.flowingims.entity.Brand;
import org.mapstruct.Mapper;

@Mapper
public interface BrandMapper {
  Brand newBrandDtoToBrand(NewBrandDTO newBrandDTO);
  Brand brandDtoToBrand(BrandDTO brandDTO);
}
