package org.bob.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "description",nullable = false) // Maps a property to a specific column name
    private String description;

    // 金額千萬不能用 double！會有精度問題（0.1 + 0.2 = 0.30000000000000004）
    @Column(name = "amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    // 用 java.time.LocalDate 取代舊的 java.util.Date
    @Column(name = "date",nullable = false)
    private LocalDate date;

    @CreationTimestamp      // 自動寫入建立時間
    @Column(name = "created_at", updatable = false) // Maps a property to a specific column name
    private LocalDateTime createdAt;

    @UpdateTimestamp        // 每次 update 自動更新
    @Column(name = "updated_at") // Maps a property to a specific column name
    private LocalDateTime updatAt;

    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name="category_id")
    private Category category;


    public Expense() {
    }

    public Expense(long id, String description, BigDecimal amount, LocalDate date, LocalDateTime createdAt, LocalDateTime updatAt, Category category) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.createdAt = createdAt;
        this.updatAt = updatAt;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
