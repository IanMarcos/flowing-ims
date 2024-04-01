package com.ianmarcos.flowingims.mapper;

import com.ianmarcos.flowingims.dto.TargetDTO;
import com.ianmarcos.flowingims.entity.Target;
import org.mapstruct.Mapper;

@Mapper
public interface TargetMapper {
  Target targetDtoToTarget(TargetDTO targetDTO);
}
