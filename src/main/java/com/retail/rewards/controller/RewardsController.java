package com.retail.rewards.controller;

import com.retail.rewards.entities.Customer;
import com.retail.rewards.model.Rewards;
import com.retail.rewards.repositories.CustomerRepository;
import com.retail.rewards.repositories.TransactionRepository;
import com.retail.rewards.service.RewardsService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/customers")
@Slf4j
public class RewardsController {

    private static final Logger logger = LoggerFactory.getLogger(RewardsController.class);
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    RewardsService rewardsService;

    /**
     * Get request to fetch reward points for customer transactions over the period of previous 3 months.
     *
     * @param customerId
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/{customerId}/rewards", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rewards> getRewardsByCustomerId(@PathVariable("customerId") int customerId) throws Exception {

        logger.info("Fetching Customer Information");
        Optional<Customer> customer = customerRepository.findById(customerId);

        if (customer.isEmpty()) {
            throw new Exception("Missing customer");
        }
        logger.info("Fetching customer rewards Info");
        Rewards rewards = rewardsService.getRewardsByCustomer(customer.get());

        return new ResponseEntity<>(rewards, HttpStatus.OK);
    }

}
