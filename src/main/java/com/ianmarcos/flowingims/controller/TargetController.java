package com.ianmarcos.flowingims.controller;

import com.ianmarcos.flowingims.entity.Target;
import com.ianmarcos.flowingims.repository.TargetRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = {"/targets", "/sexes"})
@Tag(name = "Target (sex and age group)")
public class TargetController {

  private final TargetRepository targetRepository;

  public TargetController(TargetRepository targetRepository) {
    this.targetRepository = targetRepository;
  }

  @Operation(
      summary = "Get all targets",
      description = "Get a list of all targets, E.g: Male, Girls, Unisex, etc"
  )
  @GetMapping("")
  public List<Target> getTargetList() {
    return targetRepository.findAll();
  }
}
