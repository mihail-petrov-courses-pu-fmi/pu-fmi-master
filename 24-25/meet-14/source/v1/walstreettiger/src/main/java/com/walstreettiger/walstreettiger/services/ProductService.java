package com.walstreettiger.walstreettiger.services;

import com.walstreettiger.walstreettiger.entities.Product;
import com.walstreettiger.walstreettiger.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Product createNewProduct(Product product) {
        return this.productRepository.save(product);
    }
}