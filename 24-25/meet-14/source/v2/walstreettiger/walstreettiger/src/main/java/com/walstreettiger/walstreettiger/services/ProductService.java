package com.walstreettiger.walstreettiger.services;

import com.walstreettiger.walstreettiger.entities.PortfolioProduct;
import com.walstreettiger.walstreettiger.entities.Product;
import com.walstreettiger.walstreettiger.repositories.PortfolioRepository;
import com.walstreettiger.walstreettiger.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private PortfolioRepository portfolioRepository;

    public ProductService(
            ProductRepository productRepository,
            PortfolioRepository portfolioRepository
    ) {
        this.productRepository      = productRepository;
        this.portfolioRepository    = portfolioRepository;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Product createNewProduct(Product product) {
        return this.productRepository.save(product);
    }

    public boolean buyProduct(Product product) {

        // да видя дали вече имам портфолио в базата данни
        PortfolioProduct portfolioProduct =  this.portfolioRepository.findProductByProductId(product.getId());

        if(portfolioProduct != null) {

            int productCount = portfolioProduct.getCount() + 1;
            portfolioProduct.setCount(productCount);
            this.portfolioRepository.save(portfolioProduct);
            return true;
        }

        PortfolioProduct newPortfolioProduct = new PortfolioProduct();
        newPortfolioProduct.setProductId(product.getId());
        this.portfolioRepository.save(newPortfolioProduct);
        return true;
    }

    public List<PortfolioProduct> getPortfolio() {
        return this.portfolioRepository.findAll();
    }
}