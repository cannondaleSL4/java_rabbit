package com.dmba.controller;

import com.dmba.report.CustomerBySum;
import com.dmba.repository.UsersOrderRepository;
import com.dmba.repository.wrapper.UsersOrderRepositoryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

/**
 * REST controller for handling report-related requests.
 */
@RestController
@RequestMapping("/report")
public class Report {

    @Autowired
    private UsersOrderRepositoryWrapper usersOrderRepositoryWrapper;

    /**
     * Retrieves the top 10 customers by overall sum spent.
     *
     * @return a Flux stream of CustomerBySum instances representing the top 10 customers
     */
    @GetMapping(value = "/topten", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<CustomerBySum> getAllCustomers() {
        return Flux.fromStream(usersOrderRepositoryWrapper.findTop10UsersByOverallSum()
                        .stream()
                        .map(result -> new CustomerBySum(result.getName(), result.getSum())))
                .log();
    }
}

