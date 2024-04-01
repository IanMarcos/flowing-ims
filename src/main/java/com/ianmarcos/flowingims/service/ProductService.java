package com.ianmarcos.flowingims.service;

import com.ianmarcos.flowingims.dto.NewBaseProductDTO;
import com.ianmarcos.flowingims.dto.NewProductVariantDTO;
import com.ianmarcos.flowingims.entity.Brand;
import com.ianmarcos.flowingims.entity.Product;
import com.ianmarcos.flowingims.entity.ProductVariant;
import com.ianmarcos.flowingims.exception.ResourceNotFoundException;
import com.ianmarcos.flowingims.mapper.ProductMapper;
import com.ianmarcos.flowingims.repository.BrandRepository;
import com.ianmarcos.flowingims.repository.ProductRepository;
import com.ianmarcos.flowingims.repository.ProductVariantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductVariantRepository productVariantRepo;
  private final BrandRepository brandRepository;
  private final ProductMapper productMapper;

  public ProductService(ProductRepository productRepository,
                        ProductVariantRepository productVariantRepo,
                        BrandRepository brandRepository,
                        ProductMapper productMapper)
  {
    this.productRepository = productRepository;
    this.productVariantRepo = productVariantRepo;
    this.brandRepository = brandRepository;
    this.productMapper = productMapper;
  }

  public List<Product> findAllBaseProducts() {
    return productRepository.findAllByEnabledTrue();
  }

  public Product findBaseProductById(int id) {
    return this.fetchBaseProduct(id);
  }

  public Product saveBaseProduct(NewBaseProductDTO newBaseProductDTO) {
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

  public Product updateBaseProduct(NewBaseProductDTO newBaseProductDTO, int id) {
    Product product = productMapper.newBaseProductToProduct(newBaseProductDTO);
    Product dbProduct = this.fetchBaseProduct(id);

    product.setId(id);
    product.setCreatedAt(dbProduct.getCreatedAt());
    product.setEnabled(dbProduct.isEnabled());

    return productRepository.save(product);
  }

  public void disableBaseProduct(int id) {
    Product product = this.fetchBaseProduct(id);
    product.setEnabled(false);
    productRepository.save(product);
  }

  private Product fetchBaseProduct(int id) {
    Optional<Product> dbProduct = productRepository.findByIdAndEnabledTrue(id);
    if (dbProduct.isEmpty()) {
      throw new ResourceNotFoundException("The product doesn't exist or is not active");
    }
    return dbProduct.get();
  }

  public ProductVariant saveProductVariant(NewProductVariantDTO newProduct) {
    ProductVariant product = productMapper.newProductVariantToProductVariant(newProduct);
    return productVariantRepo.save(product);
  }
}
