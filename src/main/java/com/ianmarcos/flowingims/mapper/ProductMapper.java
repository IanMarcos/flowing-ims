package com.ianmarcos.flowingims.mapper;

import com.ianmarcos.flowingims.dto.NewBaseProductDTO;
import com.ianmarcos.flowingims.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = BrandMapper.class)
public interface ProductMapper {
  @Mapping(source = "brandId", target = "brand.id")
  Product newBaseProductToProduct(NewBaseProductDTO newBaseProductDTO);
}
