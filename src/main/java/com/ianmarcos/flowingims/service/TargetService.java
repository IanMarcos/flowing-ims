package com.ianmarcos.flowingims.service;

import com.ianmarcos.flowingims.entity.Target;
import com.ianmarcos.flowingims.repository.TargetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
