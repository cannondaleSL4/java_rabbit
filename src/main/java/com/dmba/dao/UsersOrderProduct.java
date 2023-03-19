package com.dmba.dao;

import javax.persistence.*;

/**
 * Entity class representing the 'Users_Order_Product' table.
 */
@Entity
@Table(name = "Users_Order_Product")
public class UsersOrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private UsersOrder usersOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
}
