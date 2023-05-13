package com.Project.GoodToTrade.Models;

import com.Project.GoodToTrade.Enums.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String productName;

    @DecimalMin("0.0")
    private Double price;

    @NotBlank
    private String description;

    @NotBlank
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private SubCategory subcategory;

    @Enumerated(EnumType.STRING)
    private Category desiredCategory;

    @Enumerated(EnumType.STRING)
    private SubCategory desiredSubcategory;

    @ManyToOne
    @JoinColumn(name="owner_id", nullable=false)
    private Users owner;

    @OneToMany(mappedBy = "liked", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TheLikes> likes;

    public Products(String productName, Double price, String description, String imageUrl, Category category, SubCategory subcategory, Category desiredCategory, SubCategory desiredSubcategory, Users owner, List<TheLikes> likes) {
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
        this.subcategory = subcategory;
        this.desiredCategory = desiredCategory;
        this.desiredSubcategory = desiredSubcategory;
        this.owner = owner;
        this.likes = likes;
    }

    public Products() {
    }

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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

    public SubCategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(SubCategory subcategory) {
        this.subcategory = subcategory;
    }

    public Category getDesiredCategory() {
        return desiredCategory;
    }

    public void setDesiredCategory(Category desiredCategory) {
        this.desiredCategory = desiredCategory;
    }

    public SubCategory getDesiredSubcategory() {
        return desiredSubcategory;
    }

    public void setDesiredSubcategory(SubCategory desiredSubcategory) {
        this.desiredSubcategory = desiredSubcategory;
    }

    public Users getOwner() {
        return owner;
    }

    public void setOwner(Users owner) {
        this.owner = owner;
    }

    public List<TheLikes> getLikes() {
        return likes;
    }

    public void setLikes(List<TheLikes> likes) {
        this.likes = likes;
    }
}

