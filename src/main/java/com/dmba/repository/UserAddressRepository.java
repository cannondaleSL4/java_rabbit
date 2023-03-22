package com.dmba.repository;

import com.dmba.dao.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A repository interface for managing {@link UserAddress} entities.
 */
@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {

}