package com.walstreettiger.walstreettiger.repositories;

import com.walstreettiger.walstreettiger.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
