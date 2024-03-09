package com.ianmarcos.flowingims.service;

import com.ianmarcos.flowingims.entity.Brand;
import com.ianmarcos.flowingims.exception.ResourceNotFoundException;
import com.ianmarcos.flowingims.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

  private BrandRepository brandRepository;

  public BrandService(BrandRepository brandRepository) {
    this.brandRepository = brandRepository;
  }

  public List<Brand> findAll() {
    return brandRepository.findAllByEnabledTrue();
  }

  public Brand findById(int id) {
    return this.fetchBrand(id);
  }

  public Brand save(Brand brand) {
    return brandRepository.save(brand);
  }

  public Brand updateName(int id, String newName) {
    if (newName.trim().isEmpty()) { // TODO function to validate all strings
      return null; // TODO BadParamException
    }

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
