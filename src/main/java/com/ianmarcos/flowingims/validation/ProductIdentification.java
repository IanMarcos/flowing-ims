package com.ianmarcos.flowingims.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ProductIdentificationValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ProductIdentification {
  String message() default "An SKU, or UPC, or both must be provided";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
