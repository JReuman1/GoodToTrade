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

    public Products updateProduct(Long id, Products updatedProduct) {
        return productsRepository.findById(id)
                .map(product -> {
                    if(updatedProduct.getProductName() != null){
                        product.setProductName(updatedProduct.getProductName());
                    }
                    if(updatedProduct.getPrice() != null){
                        product.setPrice(updatedProduct.getPrice());
                    }
                    if(updatedProduct.getDescription() != null){
                        product.setDescription(updatedProduct.getDescription());
                    }
                    if(updatedProduct.getImageUrl() != null){
                        product.setImageUrl(updatedProduct.getImageUrl());
                    }
                    if(updatedProduct.getCategory() != null){
                        product.setCategory(updatedProduct.getCategory());
                    }
                    if(updatedProduct.getSubcategory() != null){
                        product.setSubcategory(updatedProduct.getSubcategory());
                    }
                    if(updatedProduct.getDesiredCategory() != null){
                        product.setDesiredCategory(updatedProduct.getDesiredCategory());
                    }
                    if(updatedProduct.getDesiredSubcategory() != null){
                        product.setDesiredSubcategory(updatedProduct.getDesiredSubcategory());
                    }
                    return productsRepository.save(product);
                })
                .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
    }
    public void deleteProduct(Long id) {
        if (productsRepository.existsById(id)) {
            productsRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Product not found with id: " + id);
        }
    }
}
