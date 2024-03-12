package com.ianmarcos.flowingims.validation;

import com.ianmarcos.flowingims.dto.ProductDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProductDTOIdentificationValidator implements ConstraintValidator<ProductIdentification, ProductDTO> {
  @Override
  public void initialize(ProductIdentification productIdentification) {
    ConstraintValidator.super.initialize(productIdentification);
  }

  @Override
  public boolean isValid(ProductDTO product, ConstraintValidatorContext constraintValidatorContext) {
    return product.getSku() != null || product.getUpc() != null;
  }
}
