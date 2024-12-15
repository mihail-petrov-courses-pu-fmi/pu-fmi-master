package com.walstreettiger.walstreettiger.controllers;

import com.walstreettiger.walstreettiger.entities.Product;
import com.walstreettiger.walstreettiger.http.AppResponse;
import com.walstreettiger.walstreettiger.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<?> fetchAllProducts() {

        var collection = this.productService.getAllProducts();

        return AppResponse.success()
                            .withMessage("All stocks products")
                            .withData(collection)
                            .build();
    }

    @PostMapping("/products")
    public ResponseEntity<?> createNewProduct(@RequestBody Product product) {
        var response = this.productService.createNewProduct(product);

        return AppResponse.success()
                            .withMessage("New product added to the list")
                            .withData(response)
                            .build();
    }
}
