package com.retail.rewards.service;

import com.retail.rewards.constants.RewardsConstants;
import com.retail.rewards.entities.Customer;
import com.retail.rewards.entities.Transaction;
import com.retail.rewards.model.Rewards;
import com.retail.rewards.repositories.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Slf4j
public class RewardsServiceImpl implements RewardsService {

    private static final Logger logger = LoggerFactory.getLogger(RewardsServiceImpl.class);
    @Autowired
    TransactionRepository transactionRepository;

    /**
     * Fetches all transactions for given customer and calculates reward points for period of three months until now.
     * @return Rewards
     */
    @Override
    public Rewards getRewardsByCustomer(Customer customer) {

        logger.info("Fetching transactions for customer:{}", customer.getId());
        List<Transaction> threeMonthTransactions = transactionRepository.findAllByCustomerIdAndTransactionDateBetween(
                customer.getId(), LocalDate.now().minusMonths(3), LocalDate.now());
        logger.info("Total transactions retrieved for customer:{}", threeMonthTransactions.size());
        Map<Integer, Long> rewardsByMonth = threeMonthTransactions.stream().collect(Collectors.groupingBy(e -> e.getTransactionDate().getMonthValue(), Collectors.summingLong(e -> calculateReward(e.getTransactionAmount()))));
        Long totalRewards = threeMonthTransactions.stream().map(e -> calculateReward(e.getTransactionAmount())).reduce(0L, Long::sum);

        Rewards rewards = new Rewards();
        rewards.setTotalRewards(totalRewards);
        rewards.setRewardsByMonth(rewardsByMonth);
        rewards.setCustomerId(customer.getId());
        return rewards;


    }

    /**
     * Calculates rewards based on the transaction amount.
     */
    private long calculateReward(double amount) {
        if (amount > RewardsConstants.RewardThresholdAboveFifty && amount <= RewardsConstants.RewardThresholdAboveHundred) {
            return Math.round(amount - RewardsConstants.RewardThresholdAboveFifty);
        } else if (amount > RewardsConstants.RewardThresholdAboveHundred) {
            return Math.round(amount - RewardsConstants.RewardThresholdAboveHundred) * RewardsConstants.RewardPointsAboveHundred + (RewardsConstants.RewardThresholdAboveHundred - RewardsConstants.RewardThresholdAboveFifty);
        } else {

            return 0;
        }
    }
}
