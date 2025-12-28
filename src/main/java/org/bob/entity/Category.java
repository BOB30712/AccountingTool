package org.bob.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name",nullable = false) // Maps a property to a specific column name
    private String name;

    @Column(name = "max_amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal maxAmount;

    @CreationTimestamp      // 自動寫入建立時間
    @Column(name = "created_at", updatable = false) // Maps a property to a specific column name
    private LocalDateTime createdAt;

    @UpdateTimestamp        // 每次 update 自動更新
    @Column(name = "updated_at") // Maps a property to a specific column name
    private LocalDateTime updatAt;


    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    private Set<Expense> expenses;

    public Category() {
    }

    public Category(long id, String name, BigDecimal maxAmount, LocalDateTime createdAt, LocalDateTime updatAt, Set<Expense> expenses) {
        this.id = id;
        this.name = name;
        this.maxAmount = maxAmount;
        this.createdAt = createdAt;
        this.updatAt = updatAt;
        this.expenses = expenses;
    }

    public Set<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(Set<Expense> expenses) {
        this.expenses = expenses;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatAt() {
        return updatAt;
    }

    public void setUpdatAt(LocalDateTime updatAt) {
        this.updatAt = updatAt;
    }
}
