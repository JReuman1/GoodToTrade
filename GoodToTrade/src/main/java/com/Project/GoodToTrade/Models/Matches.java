package com.Project.GoodToTrade.Models;

import jakarta.persistence.*;

@Entity
public class Matches {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user1_id", nullable=false)
    private Users user1;

    @ManyToOne
    @JoinColumn(name="user2_id", nullable=false)
    private Users user2;

    @ManyToOne
    @JoinColumn(name="product1_id", nullable=false)
    private Products product1;

    @ManyToOne
    @JoinColumn(name="product2_id", nullable=false)
    private Products product2;

    public Matches(Users user1, Users user2, Products product1, Products product2) {
        this.user1 = user1;
        this.user2 = user2;
        this.product1 = product1;
        this.product2 = product2;
    }

    public Matches() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser1() {
        return user1;
    }

    public void setUser1(Users user1) {
        this.user1 = user1;
    }

    public Users getUser2() {
        return user2;
    }

    public void setUser2(Users user2) {
        this.user2 = user2;
    }

    public Products getProduct1() {
        return product1;
    }

    public void setProduct1(Products product1) {
        this.product1 = product1;
    }

    public Products getProduct2() {
        return product2;
    }

    public void setProduct2(Products product2) {
        this.product2 = product2;
    }
}
