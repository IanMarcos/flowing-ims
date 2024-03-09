package com.ianmarcos.flowingims.service;

import com.ianmarcos.flowingims.entity.Brand;
import com.ianmarcos.flowingims.entity.Product;
import com.ianmarcos.flowingims.exception.ResourceNotFoundException;
import com.ianmarcos.flowingims.repository.BrandRepository;
import com.ianmarcos.flowingims.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

  private final String productNotFoundMessage = "The product doesn't exist or is not active";
  private ProductRepository productRepository;
  private BrandRepository brandRepository;

  public ProductService(ProductRepository productRepository, BrandRepository brandRepository) {
    this.productRepository = productRepository;
    this.brandRepository = brandRepository;
  }

  public List<Product> findAll() {
    return productRepository.findAllByEnabledTrue();
  }

  public Product findById(int id) {
    Optional<Product> product = productRepository.findByIdAndEnabledTrue(id);

    if (product.isEmpty()) {
      throw new ResourceNotFoundException(productNotFoundMessage);
    }

    return product.get();
  }

  public Product save(Product product) {
    if (product.getBrand() != null) {
      int inputBrandId = product.getBrand().getId();
      Optional<Brand> dbBrand = brandRepository.findById(inputBrandId); // TODO update called method

      if (dbBrand.isPresent()) { //TODO validate sku
        product.setBrand(dbBrand.get());
      } else {
        product.setBrand(null);
      }
    }
    return productRepository.save(product);
  }

  public Product update(Product product, int id) {
    Optional<Product> optionalProduct = productRepository.findByIdAndEnabledTrue(id);

    if (optionalProduct.isEmpty()) {
      throw new ResourceNotFoundException(productNotFoundMessage);
    }

    Product dbProd = optionalProduct.get();

    product.setId(id);
    product.setCreatedAt(dbProd.getCreatedAt());
    product.setEnabled(dbProd.isEnabled());

    return this.save(product);
  }

  public void delete(int id) {
    Optional<Product> optProduct = productRepository.findByIdAndEnabledTrue(id);

    if (optProduct.isEmpty()) {
      throw new ResourceNotFoundException(productNotFoundMessage);
    }

    Product product = optProduct.get();
    product.setEnabled(false);
    productRepository.save(product);
  }
}
