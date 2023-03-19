package com.dmba.dao;

import lombok.*;

import javax.persistence.*;

/**
 * Entity class representing the 'user_address' table.
 */
@Getter
@Setter
@Entity
@Table(name = "user_address")
@AllArgsConstructor
@NoArgsConstructor
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long addressId;

    @Column(name = "zip_code")
    private Integer zipCode;

    @Column(name = "country")
    private String country;

    @Column(name = "state")
    private String state;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "number_house")
    private Integer numberHouse;

    @Column(name = "number_apartment")
    private Integer numberApartment;
}