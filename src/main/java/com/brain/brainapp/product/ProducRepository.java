package com.brain.brainapp.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProducRepository extends JpaRepository<Product, Long> {

    Optional<Product> findProductByName(String name);
}
