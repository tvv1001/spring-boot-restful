package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private Long productId;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Double price;

  @Column
  private String description;

  @Column(nullable = false)
  private Integer quantity;

  // Constructors
  public Product() {
    this.productId = System.currentTimeMillis() + (long) (Math.random() * 100000);
  }

  public Product(String name, Double price, String description, Integer quantity) {
    this();
    this.name = name;
    this.price = price;
    this.description = description;
    this.quantity = quantity;
  }

  // Getters and Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return "Product{" +
        "id=" + id +
        ", productId=" + productId +
        ", name='" + name + '\'' +
        ", price=" + price +
        ", description='" + description + '\'' +
        ", quantity=" + quantity +
        '}';
  }
}
