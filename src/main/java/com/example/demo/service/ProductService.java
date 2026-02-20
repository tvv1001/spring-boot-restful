package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  // CREATE - Add a new product
  public Product createProduct(Product product) {
    return productRepository.save(product);
  }

  // READ - Get all products
  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  // READ - Get product by ID
  public Optional<Product> getProductById(Long id) {
    return productRepository.findById(id);
  }

  // READ - Search products by name
  public List<Product> searchProductByName(String name) {
    return productRepository.findByNameContainingIgnoreCase(name);
  }

  // READ - Find products by price range
  public List<Product> getProductsByPrice(Double price) {
    return productRepository.findByPriceLessThan(price);
  }

  // READ - Find products with quantity greater than specified
  public List<Product> getProductsByQuantity(Integer quantity) {
    return productRepository.findByQuantityGreaterThan(quantity);
  }

  // READ - Get product by productId
  public Optional<Product> getProductByProductId(Long productId) {
    return productRepository.findByProductId(productId);
  }

  // UPDATE - Update an existing product
  public Product updateProduct(Long id, Product productDetails) {
    Optional<Product> existingProduct = productRepository.findById(id);

    if (existingProduct.isPresent()) {
      Product product = existingProduct.get();

      if (productDetails.getName() != null) {
        product.setName(productDetails.getName());
      }
      if (productDetails.getPrice() != null) {
        product.setPrice(productDetails.getPrice());
      }
      if (productDetails.getDescription() != null) {
        product.setDescription(productDetails.getDescription());
      }
      if (productDetails.getQuantity() != null) {
        product.setQuantity(productDetails.getQuantity());
      }

      return productRepository.save(product);
    }

    return null;
  }

  // DELETE - Delete product by ID
  public boolean deleteProduct(Long id) {
    if (productRepository.existsById(id)) {
      productRepository.deleteById(id);
      return true;
    }
    return false;
  }

  // DELETE - Delete all products
  public void deleteAllProducts() {
    productRepository.deleteAll();
  }
}
