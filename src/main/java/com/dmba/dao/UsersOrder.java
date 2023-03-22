package com.dmba.dao;

import com.dmba.dao.proto.UsersOrderProto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Entity class representing the 'users_order' table.
 */
@Getter
@Setter
@Entity
@Table(name = "users_order")
@NoArgsConstructor
public class UsersOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "timestamp")
    private Long timeStamp;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private UserAddress userAddress;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> productList = new ArrayList<>();

    @Column(name = "payed")
    private Boolean isPayed;

    @Column(name = "account_total")
    private BigDecimal accountTotal;

    /**
     * Constructor for the UsersOrder class that takes a UsersOrderProto.UsersOrder object as input.
     * @param order The UsersOrderProto.UsersOrder object to be converted into a UsersOrder entity.
     */
    public UsersOrder(UsersOrderProto.UsersOrder order) {
        this.uuid = UUID.fromString(order.getUuid().getValue());
        this.timeStamp = Long.valueOf(order.getTimeStamp());
        this.accountTotal = BigDecimal.valueOf(order.getAccountTotal());
        this.isPayed = order.getPayed();

        for (UsersOrderProto.UsersOrder.Product  product: order.getOrderList()) {
            Product productEntity = new Product(
                    null,
                    product.getTitle(),
                    BigDecimal.valueOf(product.getPrice()),
                    product.getAmount(),
                    BigDecimal.valueOf(product.getCost())
            );
            this.productList.add(productEntity);
        }

        UserAddress userAddressEntity = new UserAddress(
                null,
                order.getAddress().getZipCode(),
                order.getAddress().getCountry(),
                order.getAddress().getState(),
                order.getAddress().getCity(),
                order.getAddress().getStreet(),
                order.getAddress().getNumberHouse(),
                order.getAddress().getNumberApartment()
        );

        this.userAddress = userAddressEntity;

        User userEntity = User.builder().
                userName(order.getUser().getUserName()).
                age(order.getUser().getAge()).
                role(order.getUser().getRole()).
                build();

        this.user = userEntity;
    }
}