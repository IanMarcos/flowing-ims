package com.ianmarcos.flowingims.controller;

import com.ianmarcos.flowingims.dto.NewBaseProductDTO;
import com.ianmarcos.flowingims.entity.Product;
import com.ianmarcos.flowingims.exception.ResourceNotFoundException;
import com.ianmarcos.flowingims.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Product")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @Operation(
      summary = "Get a list of all products",
      description = "Gets all products organized by id"
  )
  @GetMapping("")
  public List<Product> getAllProducts() {
    return productService.findAll();
  }

  @Operation(
      summary = "Get a product",
      description = "Gets the product with the matching id"
  )
  @GetMapping("/{id}")
  public Product getById(@PathVariable int id) {
    return productService.findById(id);
  }

  @Operation(
      summary = "Create a new product",
      description = "Creates a new base product"
  )
  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public Product createProduct(@RequestBody NewBaseProductDTO newProduct) {
    return productService.save(newProduct);
  }

  @Operation(
      summary = "Update a product",
      description = "Updates a product with the provided properties. A not provided property will update to null"
  )
  @PutMapping("/{id}")
  public Product updateProduct(@Valid @RequestBody NewBaseProductDTO product, @PathVariable int id) {
    if (id <= 0) {
      throw new ResourceNotFoundException("The product doesn't exist");
    }

    return productService.update(product, id);
  }

  @Operation(
      summary = "Logically delete a product",
      description = "Marks the product with the given id as disabled"
  )
  @DeleteMapping("/{id}")
  public void deleteProduct(@PathVariable int id) {
    if (id <= 0) {
      throw new ResourceNotFoundException("The product doesn't exist");
    }

    productService.delete(id);
  }
}
