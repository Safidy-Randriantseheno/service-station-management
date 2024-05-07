package com.projet1.serviceStation.service;

import com.projet1.serviceStation.model.Product;
import com.projet1.serviceStation.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    public Product updateProduct(Product product) {
        return productRepository.update(product);
    }
    public Product deleteProduct(Product id) {

        return productRepository.delete(id);
    }
}
