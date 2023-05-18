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
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductsService productsService;

    private ObjectMapper objectMapper = new ObjectMapper();

    private List<Products> productList;
    private Users owner1, owner2;

    @BeforeEach
    public void setup() {
        owner1 = new Users("user1", "User One", "user1@email.com", "1234567890", "password1", null, null);
        owner1.setId(1L);

        owner2 = new Users("user2", "User Two", "user2@email.com", "0987654321", "password2", null, null);
        owner2.setId(2L);

        Products product1 = createProduct(1L, "test1", 100.0, "test1", "test1", owner1);
        Products product2 = createProduct(2L, "test2", 200.0, "test2", "test2", owner2);

        productList = Arrays.asList(product1, product2);

        when(productsService.getProducts()).thenReturn(productList);
        when(productsService.getProduct(1L)).thenReturn(product1);
        when(productsService.getProduct(2L)).thenReturn(product2);
        when(productsService.getProductsByName("test1")).thenReturn(Arrays.asList(product1));
        when(productsService.getProductsByCategory(Category.ELECTRONICS)).thenReturn(Arrays.asList(product1));
        when(productsService.getProductsBySubcategory(SubCategory.COMPUTERS)).thenReturn(Arrays.asList(product1));
        when(productsService.getProductsByOwnerId(1L)).thenReturn(Arrays.asList(product1));
        when(productsService.saveProduct(any(Products.class))).thenReturn(product1);
    }

    @Test
    public void testGetProducts() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));
    }

    @Test
    public void testGetProduct() throws Exception {
        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void testGetProductsByName() throws Exception {
        mockMvc.perform(get("/api/products/name/test1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    public void testGetProductsByCategory() throws Exception {
        mockMvc.perform(get("/api/products/category/ELECTRONICS"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    public void testGetProductsBySubcategory() throws Exception {
        mockMvc.perform(get("/api/products/subcategory/COMPUTERS"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    public void testGetProductsByOwnerId() throws Exception {
        mockMvc.perform(get("/api/products/owner/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    public void testSaveProduct() throws Exception {
        Products newProduct = createProduct(3L, "test3", 300.0, "test3", "test3", owner1);
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newProduct)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Products updatedProduct = createProduct(1L, "updated", 150.0, "updated", "updated", owner1);
        mockMvc.perform(put("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedProduct)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        mockMvc.perform(delete("/api/products/1"))
                .andExpect(status().isNoContent());
    }

    private Products createProduct(Long id, String productName, Double price, String description, String imageUrl, Users owner) {
        Products product = new Products();
        product.setId(id);
        product.setProductName(productName);
        product.setPrice(price);
        product.setDescription(description);
        product.setImageUrl(imageUrl);
        product.setCategory(Category.ELECTRONICS);
        product.setSubcategory(SubCategory.COMPUTERS);
        product.setDesiredCategory(Category.ELECTRONICS);
        product.setDesiredSubcategory(SubCategory.COMPUTERS);
        product.setOwner(owner);
        return product;
    }
}
