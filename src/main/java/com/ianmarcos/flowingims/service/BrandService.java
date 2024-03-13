package com.ianmarcos.flowingims.service;

import com.ianmarcos.flowingims.dto.NewBrandDTO;
import com.ianmarcos.flowingims.entity.Brand;
import com.ianmarcos.flowingims.exception.ResourceNotFoundException;
import com.ianmarcos.flowingims.mapper.BrandMapper;
import com.ianmarcos.flowingims.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

  private final BrandRepository brandRepository;
  private final BrandMapper brandMapper;

  public BrandService(BrandRepository brandRepository, BrandMapper brandMapper) {
    this.brandRepository = brandRepository;
    this.brandMapper = brandMapper;
  }

  public List<Brand> findAll() {
    return brandRepository.findAllByEnabledTrue();
  }

  public Brand findById(int id) {
    return this.fetchBrand(id);
  }

  public Brand save(NewBrandDTO newBrand) {
    Brand brand = brandMapper.newBrandDtoToBrand(newBrand);
    return brandRepository.save(brand);
  }

  public Brand updateName(int id, String newName) {
    Brand brand = this.fetchBrand(id);
    brand.setName(newName);
    return brandRepository.save(brand);
  }

  public void delete(int id) {
    Brand brand = this.fetchBrand(id);
    brand.setEnabled(false);
    brandRepository.save(brand);
  }

  private Brand fetchBrand(int id) {
    Optional<Brand> dbBrand = brandRepository.findByIdAndEnabledTrue(id);
    if (dbBrand.isEmpty()) {
      throw new ResourceNotFoundException("The brand doesn't exist or is not active");
    }
    return dbBrand.get();
  }
}
