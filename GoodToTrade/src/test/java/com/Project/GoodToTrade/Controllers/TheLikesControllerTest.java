package com.Project.GoodToTrade.Controllers;

import java.util.Arrays;
import java.util.List;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import com.Project.GoodToTrade.DTOs.LikeDTO;
import com.Project.GoodToTrade.Models.Products;
import com.Project.GoodToTrade.Models.TheLikes;
import com.Project.GoodToTrade.Models.Users;
import com.Project.GoodToTrade.Services.ProductsService;
import com.Project.GoodToTrade.Services.TheLikesService;
import com.Project.GoodToTrade.Services.UsersService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@SpringBootTest
@AutoConfigureMockMvc
public class TheLikesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TheLikesService theLikesService;

    @MockBean
    private UsersService usersService;

    @MockBean
    private ProductsService productsService;

    private TheLikes like1;
    private TheLikes like2;
    private Users user;
    private Products product;

    @BeforeEach
    public void setup() {
        user = new Users("User1", "FullName1", "user1@email.com", "1234567890", "password1", null, null, null);
        user.setId(1L);

        product = new Products();
        product.setId(1L);

        like1 = new TheLikes(user, product);
        like1.setId(1L);

        like2 = new TheLikes(user, product);
        like2.setId(2L);

        Mockito.reset(theLikesService, usersService, productsService);
    }

    @Test
    @WithMockUser(value = "spring")
    public void testGetLikes() throws Exception {
        List<TheLikes> likes = Arrays.asList(like1, like2);

        when(theLikesService.getLikes()).thenReturn(likes);

        mockMvc.perform(get("/api/likes")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic(user.getUsername(), user.getPassword())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(like1.getId().intValue())))
                .andExpect(jsonPath("$[1].id", is(like2.getId().intValue())));
    }

    @Test
    @WithMockUser(value = "spring")
    public void testGetLike() throws Exception {
        when(theLikesService.getLike(1L)).thenReturn(like1);

        mockMvc.perform(get("/api/likes/1")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic(user.getUsername(), user.getPassword())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(like1.getId().intValue())));
    }

    @Test
    @WithMockUser(value = "spring")
    public void testGetLikesByUserId() throws Exception {
        List<TheLikes> likes = Arrays.asList(like1, like2);

        when(theLikesService.getLikesByUserId(1L)).thenReturn(likes);

        mockMvc.perform(get("/api/likes/likedBy/1")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic(user.getUsername(), user.getPassword())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(like1.getId().intValue())))
                .andExpect(jsonPath("$[1].id", is(like2.getId().intValue())));
    }

    @Test
    @WithMockUser(value = "spring")
    public void testGetLikesByProductId() throws Exception {
        List<TheLikes> likes = Arrays.asList(like1, like2);

        when(theLikesService.getLikesByProductId(1L)).thenReturn(likes);

        mockMvc.perform(get("/api/likes/liked/1")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic(user.getUsername(), user.getPassword())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(like1.getId().intValue())))
                .andExpect(jsonPath("$[1].id", is(like2.getId().intValue())));
    }

    @Test
    @WithMockUser(value = "spring")
    public void testSaveLike() throws Exception {
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setUserId(user.getId());
        likeDTO.setProductId(product.getId());

        when(usersService.getUser(likeDTO.getUserId())).thenReturn(user);
        when(productsService.getProduct(likeDTO.getProductId())).thenReturn(product);
        when(theLikesService.saveLike(Mockito.any(TheLikes.class))).thenReturn(like1);

        mockMvc.perform(post("/api/likes")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic(user.getUsername(), user.getPassword()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(likeDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(like1.getId().intValue())));
    }
}
