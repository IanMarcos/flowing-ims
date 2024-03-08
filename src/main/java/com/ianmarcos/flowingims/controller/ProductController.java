package com.ianmarcos.flowingims.controller;

import com.ianmarcos.flowingims.entity.Product;
import com.ianmarcos.flowingims.service.ProductService;
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
    // TODO 404 in case of null
    return productService.findById(id);
  }

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public Product createProduct(@RequestBody Product newProduct) {
    // TODO Error handler for duplicate product names
    return productService.save(newProduct);
  }

  @PutMapping("/{id}")
  public Product updateProduct(@RequestBody Product product, @PathVariable int id) {
    if (id <= 0) {
      // TODO send bad request/404
      return null;
    }

    return productService.update(product, id);
  }

  @DeleteMapping("/{id}")
  public void deleteProduct(@PathVariable int id) {
    if (id <= 0) {
      // TODO send bad request/404
      return;
    }

    productService.delete(id);
  }
}
