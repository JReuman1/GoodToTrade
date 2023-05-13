package com.Project.GoodToTrade.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String fullName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    private String password;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Products> products;

    @OneToMany(mappedBy = "likedBy", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TheLikes> likes;

    public Users(String username, String fullName, String email, String phone, String password, List<Products> products, List<TheLikes> likes) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.products = products;
        this.likes = likes;
    }

    public Users() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public List<TheLikes> getLikes() {
        return likes;
    }

    public void setLikes(List<TheLikes> likes) {
        this.likes = likes;
    }
}
