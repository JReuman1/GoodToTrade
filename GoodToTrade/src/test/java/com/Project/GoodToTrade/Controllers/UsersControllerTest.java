package com.Project.GoodToTrade.Controllers;

import com.Project.GoodToTrade.Models.Users;
import com.Project.GoodToTrade.Services.UsersService;
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

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersService usersService;

    private Users user;

    @BeforeEach
    public void setUp() {
        user = new Users();
        user.setId(1L);
        user.setUsername("testUser");
        user.setPassword("password");
        user.setFullName("Test Full Name");
        user.setEmail("testUser@example.com");
        user.setPhone("1234567890");
    }

    // Test cases for "/api/users" endpoint
    @Test
    @WithMockUser(value = "spring")
    public void getUsers_returnsUserList() throws Exception {
        when(usersService.getUsers()).thenReturn(Collections.singletonList(user));

        mockMvc.perform(get("/api/users")
                        .with(httpBasic(user.getUsername(), user.getPassword()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value(user.getUsername()));
    }

    // Test cases for "/api/users/{id}" endpoint
    @Test
    @WithMockUser(value = "spring")
    public void getUserById_returnsUser() throws Exception {
        when(usersService.getUser(user.getId())).thenReturn(user);

        mockMvc.perform(get("/api/users/{id}", user.getId())
                        .with(httpBasic(user.getUsername(), user.getPassword()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(user.getUsername()));
    }

    // Test cases for "/api/users/username/{username}" endpoint
    @Test
    @WithMockUser(value = "spring")
    public void getUserByUsername_returnsUser() throws Exception {
        when(usersService.getUserByUsername(user.getUsername())).thenReturn(user);

        mockMvc.perform(get("/api/users/username/{username}", user.getUsername())
                        .with(httpBasic(user.getUsername(), user.getPassword()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(user.getUsername()));
    }

    // Test cases for "/api/users" POST endpoint
    @Test
    @WithMockUser(value = "spring")
    public void saveUser_returnsCreatedUser() throws Exception {
        when(usersService.saveUser(user)).thenReturn(user);

        mockMvc.perform(post("/api/users")
                        .with(httpBasic(user.getUsername(), user.getPassword()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isCreated());
    }


    @Test
    public void loginWithInvalidCredentials_returnsUnauthorized() throws Exception {
        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "invalidUser")
                        .param("password", "invalidPassword"))
                .andExpect(status().isUnauthorized());
    }

    // Test cases for authentication and authorization
    @Test
    public void getUsers_unauthenticated_returnsItsOk() throws Exception {
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "spring")
    public void getUsers_authenticated_returnsOk() throws Exception {
        when(usersService.getUsers()).thenReturn(Collections.singletonList(user));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk());
    }
}
