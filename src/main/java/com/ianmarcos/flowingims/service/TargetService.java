package com.ianmarcos.flowingims.service;

import com.ianmarcos.flowingims.dto.ObjectWithNameDTO;
import com.ianmarcos.flowingims.entity.Color;
import com.ianmarcos.flowingims.entity.Target;
import com.ianmarcos.flowingims.mapper.ColorMapper;
import com.ianmarcos.flowingims.repository.ColorRepository;
import com.ianmarcos.flowingims.repository.TargetRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TargetService {

  private final TargetRepository targetRepository;

  public TargetService(TargetRepository targetRepository) {
    this.targetRepository = targetRepository;
  }

  public List<Target> findAll() {
    return targetRepository.findAll();
  }
}
