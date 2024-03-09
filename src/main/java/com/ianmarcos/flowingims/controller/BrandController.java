package com.ianmarcos.flowingims.controller;

import com.ianmarcos.flowingims.entity.Brand;
import com.ianmarcos.flowingims.exception.ResourceNotFoundException;
import com.ianmarcos.flowingims.service.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {

  private BrandService brandService;

  public BrandController(BrandService brandService) {
    this.brandService = brandService;
  }

  @GetMapping("")
  public List<Brand> getAllBrands() {
    return brandService.findAll();
  }

  @GetMapping("/{id}")
  public Brand getById(@PathVariable int id) {
    return brandService.findById(id);
  }

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public Brand createBrand(@RequestBody Brand newBrand) {
    return brandService.save(newBrand);
  }

  @PatchMapping("/{id}")
  public Brand updateBrand(@RequestBody Brand brand, @PathVariable int id) {
    if (id <= 0) {
      throw new ResourceNotFoundException("The product doesn't exist");
    }

    return brandService.updateName(id, brand.getName());
  }

  @DeleteMapping("/{id}")
  public void deleteBrand(@PathVariable int id) {
    if (id <= 0) {
      throw new ResourceNotFoundException("The product doesn't exist");
    }

    brandService.delete(id);
  }
}
