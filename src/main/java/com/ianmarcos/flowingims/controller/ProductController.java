package com.ianmarcos.flowingims.controller;

import com.ianmarcos.flowingims.entity.Product;
import com.ianmarcos.flowingims.exception.ResourceNotFoundException;
import com.ianmarcos.flowingims.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

  private ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("")
  public List<Product> getAllProducts() {
    return productService.findAll();
  }

  @GetMapping("/{id}")
  public Product getById(@PathVariable int id) {
    return productService.findById(id);
  }

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public Product createProduct(@RequestBody Product newProduct) {
    return productService.save(newProduct);
  }

  @PutMapping("/{id}")
  public Product updateProduct(@Valid @RequestBody Product product, @PathVariable int id) {
    if (id <= 0) {
      throw new ResourceNotFoundException("The product doesn't exist");
    }

    return productService.update(product, id);
  }

  @DeleteMapping("/{id}")
  public void deleteProduct(@PathVariable int id) {
    if (id <= 0) {
      throw new ResourceNotFoundException("The product doesn't exist");
    }

    productService.delete(id);
  }
}
