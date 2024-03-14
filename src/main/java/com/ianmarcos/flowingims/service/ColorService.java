package com.ianmarcos.flowingims.service;

import com.ianmarcos.flowingims.dto.ObjectWithNameDTO;
import com.ianmarcos.flowingims.entity.Color;
import com.ianmarcos.flowingims.mapper.ColorMapper;
import com.ianmarcos.flowingims.repository.ColorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColorService {

  private ColorRepository colorRepository;
  private ColorMapper colorMapper;

  public ColorService(ColorRepository colorRepository, ColorMapper colorMapper) {
    this.colorRepository = colorRepository;
    this.colorMapper = colorMapper;
  }

  public List<Color> findAll() {
    return colorRepository.findAll();
  }

  public Color findById(int id) {
    return this.fetchColor(id);
  }

  public Color save(ObjectWithNameDTO newColor) {
    Color color = colorMapper.objectWithNameToColor(newColor);
    return colorRepository.save(color);
  }

  public Color updateName(int id, String newName) {
    Color dbColor = this.fetchColor(id);
    dbColor.setName(newName);
    return colorRepository.save(dbColor);
  }

  private Color fetchColor(int id) {
    Optional<Color> dbColor = colorRepository.findById(id);

    if (dbColor.isEmpty()) {
      throw new EntityNotFoundException("Color not found");
    }
    return dbColor.get();
  }
}
