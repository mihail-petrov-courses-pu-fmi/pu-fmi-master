package com.walstreettiger.walstreettiger.repositories;

import com.walstreettiger.walstreettiger.entities.PortfolioProduct;
import com.walstreettiger.walstreettiger.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<PortfolioProduct, Integer> {

     PortfolioProduct findProductByProductId(int id);

     List<PortfolioProduct> findAll();
}
