package com.walstreettiger.walstreettiger.repositories;

import com.walstreettiger.walstreettiger.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    public Product findByCompanyNameAndId(String companyName, int id);
}
