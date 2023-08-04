package com.retail.rewards;

import com.retail.rewards.entities.Customer;
import com.retail.rewards.entities.Transaction;
import com.retail.rewards.model.Rewards;
import com.retail.rewards.repositories.CustomerRepository;
import com.retail.rewards.repositories.TransactionRepository;
import com.retail.rewards.service.RewardsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class RewardsServiceImplTests {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private RewardsServiceImpl rewardsServiceImpl;


    @Test
    void contextLoads() {
    }

    @BeforeEach
    void setMockOutput() {
        Customer customer = new Customer();
        customer.setId(1);
        Transaction transaction = new Transaction();
        transaction.setCustomerId(1L);
        transaction.setTransactionDate(LocalDate.now());
        transaction.setTransactionId(1L);
        transaction.setTransactionAmount(120);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        when(transactionRepository.findAllByCustomerIdAndTransactionDateBetween(customer.getId(), LocalDate.now().minusMonths(3), LocalDate.now())).thenReturn(transactions);
    }


    @Test
    void testGetRewardsForCustomer() {
        Customer customer = new Customer();
        customer.setId(1);
        Rewards rewards = rewardsServiceImpl.getRewardsByCustomer(customer);
        Assertions.assertEquals(rewards.getTotalRewards(), 90L);
    }


}
