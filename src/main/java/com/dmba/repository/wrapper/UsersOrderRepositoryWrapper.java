package com.dmba.repository.wrapper;

import com.dmba.report.CustomerBySum;
import com.dmba.repository.UsersOrderRepository;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * A wrapper class for the UsersOrderRepository, providing additional functionality.
 */
@AllArgsConstructor
public class UsersOrderRepositoryWrapper {

    /**
     * The instance of UsersOrderRepository.
     */
    private UsersOrderRepository usersOrderRepository;

    /**
     * Retrieves the top 10 users by overall sum.
     *
     * @return A list of {@link CustomerBySum} objects representing the top 10 users by overall sum.
     */
    public List<CustomerBySum> findTop10UsersByOverallSum() {
        List<CustomerBySum> result = new ArrayList<>();
        List<Object[]> listOfObject = usersOrderRepository.findTop10UsersByOverallSum();

        for (Object[] obj : listOfObject) {
            CustomerBySum customerBySum = new CustomerBySum((String) obj[0], (BigDecimal) obj[1]);
            result.add(customerBySum);
        }

        return result;
    }
}

