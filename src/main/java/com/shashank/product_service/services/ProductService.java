package com.shashank.product_service.services;

import com.shashank.product_service.entities.Product;
import com.shashank.product_service.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public  ProductService(ProductRepository productRepository) { // Constructor Injection
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public Optional<Product> updateProductById(Long id, Product product) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent()) {
            return Optional.empty();
        }
        Product existingProduct = productOptional.get();
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());
        return Optional.of(productRepository.save(product));
    }

    public boolean deleteProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent()) {
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }
}
