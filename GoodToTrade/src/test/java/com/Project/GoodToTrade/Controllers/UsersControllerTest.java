package com.Project.GoodToTrade.Controllers;

import com.Project.GoodToTrade.Models.Users;
import com.Project.GoodToTrade.Services.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersService usersService;

    private Users user1;
    private Users user2;

    @BeforeEach
    public void setup() {
        user1 = new Users("User1", "FullName1", "user1@email.com", "1234567890", "password1", null, null);
        user1.setId(1L);

        user2 = new Users("User2", "FullName2", "user2@email.com", "0987654321", "password2", null, null);
        user2.setId(2L);

        Mockito.reset(usersService);
    }

    @Test
    public void testGetUsers() throws Exception {
        List<Users> users = Arrays.asList(user1, user2);

        when(usersService.getUsers()).thenReturn(users);

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].username", is(user1.getUsername())))
                .andExpect(jsonPath("$[1].username", is(user2.getUsername())));
    }

    @Test
    public void testGetUser() throws Exception {
        when(usersService.getUser(1L)).thenReturn(user1);

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(user1.getUsername())));
        System.out.println(user1.getUsername());
    }

    @Test
    public void testGetUserByUsername() throws Exception {
        when(usersService.getUserByUsername("User1")).thenReturn(user1);

        mockMvc.perform(get("/api/users/username/User1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(user1.getUsername())));
    }

    @Test
    public void testSaveUser() throws Exception {
        when(usersService.saveUser(any(Users.class))).thenReturn(user1);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username", is(user1.getUsername())));
    }

    @Test
    public void testUpdateUser() throws Exception {
        Users userUpdated = new Users("User1Updated", "FullNameUpdated", "userUpdated@email.com", "0987654321", "passwordUpdated", null, null);
        userUpdated.setId(1L);

        when(usersService.getUser(1L)).thenReturn(user1);
        when(usersService.saveUser(any(Users.class))).thenReturn(userUpdated);

        mockMvc.perform(put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userUpdated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(userUpdated.getUsername())));
    }

    @Test
    public void testDeleteUser() throws Exception {
        Mockito.doNothing().when(usersService).deleteUser(1L);

        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isNoContent());
    }
}
