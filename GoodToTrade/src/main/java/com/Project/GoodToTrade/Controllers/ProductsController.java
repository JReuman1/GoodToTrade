package com.Project.GoodToTrade.Controllers;

import com.Project.GoodToTrade.Models.Products;
import com.Project.GoodToTrade.Services.ProductsService;
import com.Project.GoodToTrade.Enums.Category;
import com.Project.GoodToTrade.Enums.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    private final ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public ResponseEntity<List<Products>> getProducts() {
        return new ResponseEntity<>(productsService.getProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> getProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productsService.getProduct(id), HttpStatus.OK);
    }

    @GetMapping("/name/{productName}")
    public ResponseEntity<List<Products>> getProductsByName(@PathVariable String productName) {
        return new ResponseEntity<>(productsService.getProductsByName(productName), HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Products>> getProductsByCategory(@PathVariable Category category) {
        return new ResponseEntity<>(productsService.getProductsByCategory(category), HttpStatus.OK);
    }

    @GetMapping("/subcategory/{subcategory}")
    public ResponseEntity<List<Products>> getProductsBySubcategory(@PathVariable SubCategory subcategory) {
        return new ResponseEntity<>(productsService.getProductsBySubcategory(subcategory), HttpStatus.OK);
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Products>> getProductsByOwnerId(@PathVariable Long ownerId) {
        return new ResponseEntity<>(productsService.getProductsByOwnerId(ownerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Products> saveProduct(@RequestBody Products product) {
        return new ResponseEntity<>(productsService.saveProduct(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable Long id, @RequestBody Products updatedProduct) {
        Products currentProduct = productsService.getProduct(id);
        currentProduct.setProductName(updatedProduct.getProductName());
        currentProduct.setPrice(updatedProduct.getPrice());
        currentProduct.setDescription(updatedProduct.getDescription());
        currentProduct.setImageUrl(updatedProduct.getImageUrl());
        currentProduct.setCategory(updatedProduct.getCategory());
        currentProduct.setSubcategory(updatedProduct.getSubcategory());
        currentProduct.setDesiredCategory(updatedProduct.getDesiredCategory());
        currentProduct.setDesiredSubcategory(updatedProduct.getDesiredSubcategory());
        currentProduct.setOwner(updatedProduct.getOwner());
        currentProduct.setLikes(updatedProduct.getLikes());
        return new ResponseEntity<>(productsService.saveProduct(currentProduct), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productsService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
