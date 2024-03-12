package com.ianmarcos.flowingims.mapper;

import com.ianmarcos.flowingims.dto.ProductDTO;
import com.ianmarcos.flowingims.entity.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {
  Product toProduct(ProductDTO product);
}
