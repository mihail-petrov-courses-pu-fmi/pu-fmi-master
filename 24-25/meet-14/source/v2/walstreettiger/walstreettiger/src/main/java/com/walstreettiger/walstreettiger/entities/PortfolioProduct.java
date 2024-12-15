package com.walstreettiger.walstreettiger.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "td_portfolio")
@Getter
@Setter
@Data
@NoArgsConstructor
public class PortfolioProduct {

    @Id
    @Column(name = "product_id")
    private int productId;

    @Column(name = "count")
    private int count = 1;

    @Column(name = "is_active")
    private int isActive = 1;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
}