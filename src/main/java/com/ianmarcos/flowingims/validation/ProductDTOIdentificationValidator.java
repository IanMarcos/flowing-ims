package com.ianmarcos.flowingims.validation;

import com.ianmarcos.flowingims.dto.NewBaseProductDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProductDTOIdentificationValidator implements ConstraintValidator<ProductIdentification, NewBaseProductDTO> {
  @Override
  public void initialize(ProductIdentification productIdentification) {
    ConstraintValidator.super.initialize(productIdentification);
  }

  @Override
  public boolean isValid(NewBaseProductDTO product, ConstraintValidatorContext constraintValidatorContext) {
    return product.getSku() != null || product.getUpc() != null;
  }
}
