package com.ianmarcos.flowingims.service;

import com.ianmarcos.flowingims.dto.NewBaseProductDTO;
import com.ianmarcos.flowingims.entity.Brand;
import com.ianmarcos.flowingims.entity.Product;
import com.ianmarcos.flowingims.exception.ResourceNotFoundException;
import com.ianmarcos.flowingims.mapper.ProductMapper;
import com.ianmarcos.flowingims.repository.BrandRepository;
import com.ianmarcos.flowingims.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

  private final ProductRepository productRepository;
  private final BrandRepository brandRepository;
  private final ProductMapper productMapper;

  public ProductService(ProductRepository productRepository, BrandRepository brandRepository, ProductMapper productMapper) {
    this.productRepository = productRepository;
    this.brandRepository = brandRepository;
    this.productMapper = productMapper;
  }

  public List<Product> findAll() {
    return productRepository.findAllByEnabledTrue();
  }

  public Product findById(int id) {
    return this.fetchProduct(id);
  }

  public Product save(NewBaseProductDTO newBaseProductDTO) {
    Product product = productMapper.newBaseProductToProduct(newBaseProductDTO);
    if (product.getBrand() != null) {
      int inputBrandId = product.getBrand().getId();
      Optional<Brand> dbBrand = brandRepository.findByIdAndEnabledTrue(inputBrandId);

      if (dbBrand.isPresent()) {
        product.setBrand(dbBrand.get());
      } else {
        product.setBrand(null);
      }
    }
    return productRepository.save(product);
  }

  public Product update(NewBaseProductDTO newBaseProductDTO, int id) {
    Product product = productMapper.newBaseProductToProduct(newBaseProductDTO);
    Product dbProduct = this.fetchProduct(id);

    product.setId(id);
    product.setCreatedAt(dbProduct.getCreatedAt());
    product.setEnabled(dbProduct.isEnabled());

    return productRepository.save(product);
  }

  public void delete(int id) {
    Product product = this.fetchProduct(id);
    product.setEnabled(false);
    productRepository.save(product);
  }

  private Product fetchProduct(int id) {
    Optional<Product> dbProduct = productRepository.findByIdAndEnabledTrue(id);
    if (dbProduct.isEmpty()) {
      throw new ResourceNotFoundException("The product doesn't exist or is not active");
    }
    return dbProduct.get();
  }
}
