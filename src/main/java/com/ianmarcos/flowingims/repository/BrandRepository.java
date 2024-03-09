package com.ianmarcos.flowingims.repository;

import com.ianmarcos.flowingims.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
  List<Brand> findAllByEnabledTrue();
  Optional<Brand> findByIdAndEnabledTrue(Integer id);
}
