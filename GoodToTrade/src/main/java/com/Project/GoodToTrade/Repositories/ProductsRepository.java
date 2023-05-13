package com.Project.GoodToTrade.Repositories;

import com.Project.GoodToTrade.Enums.Category;
import com.Project.GoodToTrade.Enums.SubCategory;
import com.Project.GoodToTrade.Models.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products, Long> {
    List<Products> findByProductName(String productName);
    List<Products> findByCategory(Category category);
    List<Products> findBySubcategory(SubCategory subcategory);
    List<Products> findByOwner_Id(Long ownerId);
}
