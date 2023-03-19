package com.dmba.dao;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Entity class representing the 'product' table.
 */
@Getter
@Setter
@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long productId;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "cost")
    private BigDecimal cost;
}
