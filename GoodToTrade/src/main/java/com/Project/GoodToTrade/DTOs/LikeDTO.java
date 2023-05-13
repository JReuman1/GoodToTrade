package com.Project.GoodToTrade.DTOs;

public class LikeDTO {

    private Long userId;
    private Long productId;

    public LikeDTO(Long userId, Long productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public LikeDTO() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
