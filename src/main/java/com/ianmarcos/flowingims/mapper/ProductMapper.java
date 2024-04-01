package com.ianmarcos.flowingims.mapper;

import com.ianmarcos.flowingims.dto.NewBaseProductDTO;
import com.ianmarcos.flowingims.dto.NewProductVariantDTO;
import com.ianmarcos.flowingims.dto.ProductDTO;
import com.ianmarcos.flowingims.entity.Product;
import com.ianmarcos.flowingims.entity.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = {
    SizeMapper.class, ColorMapper.class,
    TargetMapper.class, BrandMapper.class})
public interface ProductMapper {
  @Mapping(source = "brandId", target = "brand.id")
  Product newBaseProductToProduct(NewBaseProductDTO newBaseProductDTO);

  Product productDtoToProduct(ProductDTO productDTO);

  @Mappings({
      @Mapping(source = "productId", target = "product.id"),
      @Mapping(source = "sizeId", target = "size.id"),
      @Mapping(source = "colorId", target = "color.id"),
      @Mapping(source = "target", target = "target.id")
  })
  ProductVariant newProductVariantToProductVariant(NewProductVariantDTO newProductVariantDTO);
}
