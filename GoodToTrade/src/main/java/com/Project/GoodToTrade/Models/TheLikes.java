package com.Project.GoodToTrade.Models;
import jakarta.persistence.*;

@Entity
public class TheLikes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="likedBy_id", nullable=false)
    private Users likedBy;

    @ManyToOne
    @JoinColumn(name="liked_id", nullable=false)
    private Products liked;

    public TheLikes(Users likedBy, Products liked) {
        this.likedBy = likedBy;
        this.liked = liked;
    }

    public TheLikes() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(Users likedBy) {
        this.likedBy = likedBy;
    }

    public Products getLiked() {
        return liked;
    }

    public void setLiked(Products liked) {
        this.liked = liked;
    }
}
