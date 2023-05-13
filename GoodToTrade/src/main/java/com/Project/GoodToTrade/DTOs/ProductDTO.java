package com.Project.GoodToTrade.DTOs;

import com.Project.GoodToTrade.Enums.Category;
import com.Project.GoodToTrade.Enums.SubCategory;

public class ProductDTO {

    private Long id;
    private String productName;
    private double price;
    private String description;
    private String imageUrl;
    private Category category;
    private SubCategory subCategory;
    private Category desiredCategory;
    private SubCategory desiredSubCategory;
    private Long ownerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public Category getDesiredCategory() {
        return desiredCategory;
    }

    public void setDesiredCategory(Category desiredCategory) {
        this.desiredCategory = desiredCategory;
    }

    public SubCategory getDesiredSubCategory() {
        return desiredSubCategory;
    }

    public void setDesiredSubCategory(SubCategory desiredSubCategory) {
        this.desiredSubCategory = desiredSubCategory;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
