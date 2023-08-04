package com.retail.rewards.service;

import com.retail.rewards.entities.Customer;
import com.retail.rewards.model.Rewards;
import org.springframework.stereotype.Component;

@Component
public interface RewardsService {

    public Rewards getRewardsByCustomer(Customer customer);

}
