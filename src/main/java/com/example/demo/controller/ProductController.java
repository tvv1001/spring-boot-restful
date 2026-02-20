package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001" }, allowCredentials = "true")
public class ProductController {

  @Autowired
  private ProductService productService;

  // CREATE - Add a new product
  @PostMapping
  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    Product createdProduct = productService.createProduct(product);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
  }

  // DELETE - Delete all products
  @DeleteMapping
  public ResponseEntity<String> deleteAllProducts() {
    productService.deleteAllProducts();
    return ResponseEntity.ok("All products have been deleted successfully.");
  }

  // READ - Search products by name (MUST be before /{id} to avoid routing
  // conflicts)
  @GetMapping("/search/name")
  public ResponseEntity<List<Product>> searchByName(@RequestParam String name) {
    List<Product> products = productService.searchProductByName(name);
    return ResponseEntity.ok(products);
  }

  // READ - Get products by maximum price (MUST be before /{id} to avoid routing
  // conflicts)
  @GetMapping("/search/price")
  public ResponseEntity<List<Product>> searchByPrice(@RequestParam Double maxPrice) {
    List<Product> products = productService.getProductsByPrice(maxPrice);
    return ResponseEntity.ok(products);
  }

  // READ - Get products with quantity greater than specified (MUST be before
  // /{id} to avoid routing conflicts)
  @GetMapping("/search/quantity")
  public ResponseEntity<List<Product>> searchByQuantity(@RequestParam Integer minQuantity) {
    List<Product> products = productService.getProductsByQuantity(minQuantity);
    return ResponseEntity.ok(products);
  }

  // READ - Search products by productId (MUST be before /{id} to avoid routing
  // conflicts)
  @GetMapping("/search/productId")
  public ResponseEntity<Product> searchByProductId(@RequestParam Long productId) {
    Optional<Product> product = productService.getProductByProductId(productId);
    if (product.isPresent()) {
      return ResponseEntity.ok(product.get());
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  // READ - Get all products
  @GetMapping
  public ResponseEntity<List<Product>> getAllProducts() {
    List<Product> products = productService.getAllProducts();
    return ResponseEntity.ok(products);
  }

  // READ - Get product by ID
  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable Long id) {
    Optional<Product> product = productService.getProductById(id);

    if (product.isPresent()) {
      return ResponseEntity.ok(product.get());
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  // UPDATE - Update product by ID (Full update)
  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(
      @PathVariable Long id,
      @RequestBody Product productDetails) {
    Product updatedProduct = productService.updateProduct(id, productDetails);

    if (updatedProduct != null) {
      return ResponseEntity.ok(updatedProduct);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  // UPDATE - Partial update (PATCH)
  @PatchMapping("/{id}")
  public ResponseEntity<Product> patchProduct(
      @PathVariable Long id,
      @RequestBody Product productDetails) {
    Product updatedProduct = productService.updateProduct(id, productDetails);

    if (updatedProduct != null) {
      return ResponseEntity.ok(updatedProduct);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  // DELETE - Delete product by ID
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
    boolean isDeleted = productService.deleteProduct(id);

    if (isDeleted) {
      return ResponseEntity.ok("Product with ID " + id + " has been deleted successfully.");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with ID " + id + " not found.");
    }
  }
}
