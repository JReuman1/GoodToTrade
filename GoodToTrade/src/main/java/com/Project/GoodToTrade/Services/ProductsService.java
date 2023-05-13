package com.Project.GoodToTrade.Services;

import com.Project.GoodToTrade.Enums.Category;
import com.Project.GoodToTrade.Enums.SubCategory;
import com.Project.GoodToTrade.Models.Products;
import com.Project.GoodToTrade.Repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductsService {

    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Products> getProducts() {
        return productsRepository.findAll();
    }

    public Products getProduct(Long id) {
        return productsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
    }

    public List<Products> getProductsByName(String name) {
        return productsRepository.findByProductName(name);
    }

    public List<Products> getProductsByCategory(Category category) {
        return productsRepository.findByCategory(category);
    }

    public List<Products> getProductsBySubcategory(SubCategory subcategory) {
        return productsRepository.findBySubcategory(subcategory);
    }

    public List<Products> getProductsByOwnerId(Long ownerId) {
        return productsRepository.findByOwner_Id(ownerId);
    }

    public Products saveProduct(Products product) {
        return productsRepository.save(product);
    }

    public void deleteProduct(Long id) {
        if (productsRepository.existsById(id)) {
            productsRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Product not found with id: " + id);
        }
    }
}
