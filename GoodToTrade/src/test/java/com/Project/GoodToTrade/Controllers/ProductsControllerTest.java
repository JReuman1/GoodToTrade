package com.Project.GoodToTrade.Controllers;

import com.Project.GoodToTrade.Controllers.ProductsController;
import com.Project.GoodToTrade.Models.Products;
import com.Project.GoodToTrade.Models.Users;
import com.Project.GoodToTrade.Enums.Category;
import com.Project.GoodToTrade.Enums.SubCategory;
import com.Project.GoodToTrade.Services.ProductsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductsService productsService;

    private Products product;
    private Users owner;

    @BeforeEach
    public void setUp() {
        owner = new Users("user1", "User One", "user1@email.com", "1234567890", "password1", null, null, null);
        owner.setId(1L);

        product = new Products();
        product.setId(1L);
        product.setProductName("testProduct");
        product.setPrice(100.0);
        product.setDescription("testDescription");
        product.setImageUrl("testImageUrl");
        product.setCategory(Category.ELECTRONICS);
        product.setSubcategory(SubCategory.COMPUTERS);
        product.setDesiredCategory(Category.ELECTRONICS);
        product.setDesiredSubcategory(SubCategory.COMPUTERS);
        product.setOwner(owner);
    }

    // Test cases for "/api/products" endpoint
    @Test
    @WithMockUser(value = "spring")
    public void getProducts_returnsProductList() throws Exception {
        when(productsService.getProducts()).thenReturn(Collections.singletonList(product));

        mockMvc.perform(get("/api/products")
                        .with(httpBasic(owner.getUsername(), owner.getPassword()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productName").value(product.getProductName()));
    }

    // Test cases for "/api/products/{id}" endpoint
    @Test
    @WithMockUser(value = "spring")
    public void getProductById_returnsProduct() throws Exception {
        when(productsService.getProduct(product.getId())).thenReturn(product);

        mockMvc.perform(get("/api/products/{id}", product.getId())
                        .with(httpBasic(owner.getUsername(), owner.getPassword()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value(product.getProductName()));
    }

    // Test cases for "/api/products/name/{name}" endpoint
    @Test
    @WithMockUser(value = "spring")
    public void getProductsByName_returnsProduct() throws Exception {
        when(productsService.getProductsByName(product.getProductName())).thenReturn(Collections.singletonList(product));

        mockMvc.perform(get("/api/products/name/{name}", product.getProductName())
                        .with(httpBasic(owner.getUsername(), owner.getPassword()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productName").value(product.getProductName()));
    }

    // Test cases for "/api/products/category/{category}" endpoint
    @Test
    @WithMockUser(value = "spring")
    public void getProductsByCategory_returnsProduct() throws Exception {
        when(productsService.getProductsByCategory(product.getCategory())).thenReturn(Collections.singletonList(product));

        mockMvc.perform(get("/api/products/category/{category}", product.getCategory().name())
                        .with(httpBasic(owner.getUsername(), owner.getPassword()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].category").value(product.getCategory().name()));
    }

    // Test cases for "/api/products/subcategory/{subcategory}" endpoint
    @Test
    @WithMockUser(value = "spring")
    public void getProductsBySubcategory_returnsProduct() throws Exception {
        when(productsService.getProductsBySubcategory(product.getSubcategory())).thenReturn(Collections.singletonList(product));

        mockMvc.perform(get("/api/products/subcategory/{subcategory}", product.getSubcategory().name())
                        .with(httpBasic(owner.getUsername(), owner.getPassword()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].subcategory").value(product.getSubcategory().name()));
    }

    // Test cases for "/api/products/owner/{ownerId}" endpoint
    @Test
    @WithMockUser(value = "spring")
    public void getProductsByOwnerId_returnsProduct() throws Exception {
        when(productsService.getProductsByOwnerId(owner.getId())).thenReturn(Collections.singletonList(product));

        mockMvc.perform(get("/api/products/owner/{ownerId}", owner.getId())
                        .with(httpBasic(owner.getUsername(), owner.getPassword()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].owner.id").value(owner.getId()));
    }

    // Test cases for POST "/api/products" endpoint
    @Test
    @WithMockUser(value = "spring")
    public void saveProduct_returnsProduct() throws Exception {
        when(productsService.saveProduct(any(Products.class))).thenReturn(product);

        mockMvc.perform(post("/api/products")
                        .with(httpBasic(owner.getUsername(), owner.getPassword()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.productName").value(product.getProductName()));
    }

}
