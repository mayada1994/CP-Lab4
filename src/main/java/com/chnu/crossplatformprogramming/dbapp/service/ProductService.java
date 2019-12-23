package com.chnu.crossplatformprogramming.dbapp.service;

import com.chnu.crossplatformprogramming.dbapp.model.Product;
import com.chnu.crossplatformprogramming.dbapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void saveProduct(int id, String title, double cost, int count, int shopId) {
        productRepository.saveProduct(id, title, cost, count, shopId);
    }

    public List<Product> getAllProductByShopId(int shopId) {
        return productRepository.getAllProductByShopId(shopId);
    }

    public Product getProductById(int productId) {
        return productRepository.getProductById(productId);
    }

    public List<Integer> getAllProductIds() {
        return productRepository.getAllProductIds();
    }

    public void updateProduct(int productId, String title, double cost, int count) {
        productRepository.updateProduct(productId, title, cost, count);
    }

    public void deleteProduct(int productId) {
        productRepository.deleteProduct(productId);
    }
}