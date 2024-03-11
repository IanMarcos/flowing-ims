package com.ianmarcos.flowingims.validation;

import com.ianmarcos.flowingims.entity.Product;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProductIdentificationValidator implements ConstraintValidator<ProductIdentification, Product> {
  @Override
  public void initialize(ProductIdentification productIdentification) {
    ConstraintValidator.super.initialize(productIdentification);
  }

  @Override
  public boolean isValid(Product product, ConstraintValidatorContext constraintValidatorContext) {
    return product.getSku() != null || product.getBrand() != null;
  }
}
