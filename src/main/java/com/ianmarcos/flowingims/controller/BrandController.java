package com.ianmarcos.flowingims.controller;

import com.ianmarcos.flowingims.entity.Brand;
import com.ianmarcos.flowingims.exception.ResourceNotFoundException;
import com.ianmarcos.flowingims.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
@Tag(name = "Brand")
public class BrandController {

  private final BrandService brandService;

  public BrandController(BrandService brandService) {
    this.brandService = brandService;
  }

  @Operation(
      summary = "Get a list of all brands",
      description = "Gets all brands organized by id"
  )
  @GetMapping("")
  public List<Brand> getAllBrands() {
    return brandService.findAll();
  }

  @Operation(
      summary = "Get a brand",
      description = "Gets the brand with the matching id"
  )
  @GetMapping("/{id}")
  public Brand getById(@PathVariable int id) {
    if (id <= 0) {
      throw new ResourceNotFoundException("The brand doesn't exist");
    }

    return brandService.findById(id);
  }

  @Operation(
      summary = "Create a new brand",
      description = "Creates a new brand. Only the name must be provided"
  )
  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public Brand createBrand(@RequestBody Brand newBrand) {
    return brandService.save(newBrand);
  }

  @Operation(
      summary = "Update a brand's name"
  )
  @PatchMapping("/{id}")
  public Brand updateBrand(@Valid @RequestBody Brand brand, @PathVariable int id) {
    if (id <= 0) {
      throw new ResourceNotFoundException("The brand doesn't exist");
    }

    return brandService.updateName(id, brand.getName());
  }

  @Operation(
      summary = "Logically delete a brand",
      description = "Marks the brand with the given id as disabled"
  )
  @DeleteMapping("/{id}")
  public void deleteBrand(@PathVariable int id) {
    if (id <= 0) {
      throw new ResourceNotFoundException("The brand doesn't exist");
    }

    brandService.delete(id);
  }
}
