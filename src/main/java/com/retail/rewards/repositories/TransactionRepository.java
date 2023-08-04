package com.retail.rewards.repositories;


import com.retail.rewards.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    public List<Transaction> findAllByCustomerIdAndTransactionDateBetween(int customerId, LocalDate startDate, LocalDate endDate);
}
