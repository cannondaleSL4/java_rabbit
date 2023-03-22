package com.dmba.repository;

import com.dmba.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A repository interface for managing {@link User} entities.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}