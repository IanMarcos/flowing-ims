package com.ianmarcos.flowingims.controller;

import com.ianmarcos.flowingims.dto.ObjectWithNameDTO;
import com.ianmarcos.flowingims.entity.Size;
import com.ianmarcos.flowingims.exception.ResourceNotFoundException;
import com.ianmarcos.flowingims.service.SizeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sizes")
@Tag(name = "Size")
public class SizeController {

  private SizeService sizeService;

  public SizeController(SizeService sizeService) {
    this.sizeService = sizeService;
  }

  @Operation(
      summary = "Get a list of all sizes"
  )
  @GetMapping("")
  public List<Size> getAllSizes() {
    return sizeService.findAll();
  }

  @Operation(
      summary = "Get a size value",
      description = "Gets the size with the matching id"
  )
  @GetMapping("/{id}")
  public Size getById(@PathVariable int id) {
    if (id <= 0) {
      throw new ResourceNotFoundException("The color doesn't exist");
    }

    return sizeService.findById(id);
  }

  @Operation(
      summary = "Create a new Size",
      description = "Creates a new size. Only the name must be provided"
  )
  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public Size createSize(@RequestBody ObjectWithNameDTO newSize) {
    return sizeService.save(newSize);
  }

  @Operation(
      summary = "Update a size's name"
  )
  @PatchMapping("/{id}")
  public Size updateColor(@Valid @RequestBody ObjectWithNameDTO newSize, @PathVariable int id) {
    if (id <= 0) {
      throw new ResourceNotFoundException("The size doesn't exist");
    }

    return sizeService.updateValue(id, newSize.getName());
  }
}
