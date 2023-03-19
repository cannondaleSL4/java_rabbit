package com.dmba.repository;

import com.dmba.dao.UsersOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * A repository interface for managing {@link UsersOrder} entities.
 */
@Repository
public interface UsersOrderRepository extends JpaRepository<UsersOrder, UUID> {

    /**
     * Finds the top 10 users with the highest overall sum of their account totals.
     *
     * @return A list of Object arrays, where each array contains the user's name
     *         and their overall sum.
     */
    @Query(value = "SELECT ut.user_name, SUM(uo.account_total) as overall_sum\n" +
            "FROM User_Table ut\n" +
            "JOIN Users_Order uo ON ut.id = uo.user_id\n" +
            "GROUP BY ut.user_name\n" +
            "ORDER BY overall_sum DESC\n" +
            "LIMIT 10", nativeQuery = true)
    List<Object[]> findTop10UsersByOverallSum();

    /**
     * Finds a {@link UsersOrder} entity by its UUID and timestamp.
     *
     * @param uuid      The UUID of the UsersOrder entity.
     * @param timestamp The timestamp of the UsersOrder entity.
     * @return The UsersOrder entity if found, otherwise null.
     */
    UsersOrder findByUuidAndTimeStamp(UUID uuid, Long timestamp);
}