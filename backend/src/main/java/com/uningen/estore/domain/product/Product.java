package com.uningen.estore.domain.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Product name should not be blank")
    @NotNull(message = "Product name should not be null")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Product description should not be blank")
    @NotNull(message = "Product description should not be null")
    @Column(name = "description")
    private String description;

    @Positive(message = "Product price must be greater than zero")
    @Column(name = "price")
    private double price;

    @NotBlank(message = "Product picture url should not be blank")
    @NotNull(message = "Product picture url should not be null")
    @Column(name = "picture_url")
    private String pictureUrl;

    @NotBlank(message = "Product category should not be blank")
    @NotNull(message = "Product category should not be null")
    @Column(name = "category")
    private String category;

    @NotBlank(message = "Product brand should not be blank")
    @NotNull(message = "Product brand should not be null")
    @Column(name = "brand")
    private String brand;

    @PositiveOrZero(message = "Product quantity cannot be negative")
    @Column(name = "quantity_in_stock")
    private int quantityInStock;

    @CreatedDate
    @Column(name = "created_date")
    Instant createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    Instant lastModifiedDate;

    @Version
    @Column(name = "version")
    int version;

    public static Product of(String name, String description, double price, String pictureUrl, String category, String brand, int quantityInStock) {
        return new Product(null, name, description, price, pictureUrl, category, brand, quantityInStock, null, null, 0);
    }
}

