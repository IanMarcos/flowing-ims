package com.ianmarcos.flowingims.service;

import com.ianmarcos.flowingims.dto.ObjectWithNameDTO;
import com.ianmarcos.flowingims.entity.Size;
import com.ianmarcos.flowingims.mapper.SizeMapper;
import com.ianmarcos.flowingims.repository.SizeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SizeService {

  private final SizeRepository sizeRepository;
  private final SizeMapper sizeMapper;

  public SizeService(SizeRepository sizeRepository, SizeMapper sizeMapper) {
    this.sizeRepository = sizeRepository;
    this.sizeMapper = sizeMapper;
  }

  public List<Size> findAll() {
    return sizeRepository.findAll();
  }

  public Size findById(int id) {
    return this.fetchSize(id);
  }

  public Size save(ObjectWithNameDTO newSize) {
    Size size = sizeMapper.objectWithNameToSize(newSize);
    return sizeRepository.save(size);
  }

  public Size updateValue(int id, String newValue) {
    Size dbSize = this.fetchSize(id);
    dbSize.setValue(newValue);
    return sizeRepository.save(dbSize);
  }

  private Size fetchSize(int id) {
    Optional<Size> dbSize = sizeRepository.findById(id);

    if (dbSize.isEmpty()) {
      throw new EntityNotFoundException("Size not found");
    }
    return dbSize.get();
  }
}
