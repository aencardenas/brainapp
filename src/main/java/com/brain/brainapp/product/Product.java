package com.brain.brainapp.product;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "price")
    private float price;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDate createdAt;

    // Empty Constructor
    public Product() {
    }

    // Complete constructor
    public Product(Long id, String name, float price, LocalDate createdAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.createdAt = createdAt;
    }

    // Final constructor
    public Product(String name, float price, LocalDate createdAt) {
        this.name = name;
        this.price = price;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public LocalDate getCreated_at() {
        return createdAt;
    }

    public void setCreated_at(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
