package com.dmba.repository;

import com.dmba.dao.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A repository interface for managing {@link Product} entities.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}