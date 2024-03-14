package com.ianmarcos.flowingims.controller;

import com.ianmarcos.flowingims.dto.ObjectWithNameDTO;
import com.ianmarcos.flowingims.entity.Color;
import com.ianmarcos.flowingims.exception.ResourceNotFoundException;
import com.ianmarcos.flowingims.service.ColorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colors")
@Tag(name = "Color")
public class ColorController {

  private ColorService colorService;

  public ColorController(ColorService colorService) {
    this.colorService = colorService;
  }

  @Operation(
      summary = "Get a list of all colors",
      description = "Gets all brands organized by name"
  )
  @GetMapping("")
  public List<Color> getAllColors() {
    return colorService.findAll();
  }

  @Operation(
      summary = "Get a color",
      description = "Gets the color with the matching id"
  )
  @GetMapping("/{id}")
  public Color getById(@PathVariable int id) {
    if (id <= 0) {
      throw new ResourceNotFoundException("The color doesn't exist");
    }

    return colorService.findById(id);
  }

  @Operation(
      summary = "Create a new color",
      description = "Creates a new color. Only the name must be provided"
  )
  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public Color createColor(@RequestBody ObjectWithNameDTO newColor) {
    return colorService.save(newColor);
  }

  @Operation(
      summary = "Update a color's name"
  )
  @PatchMapping("/{id}")
  public Color updateColor(@Valid @RequestBody ObjectWithNameDTO newColor, @PathVariable int id) {
    if (id <= 0) {
      throw new ResourceNotFoundException("The color doesn't exist");
    }

    return colorService.updateName(id, newColor.getName());
  }
}
