package com.retail.rewards.model;

import com.retail.rewards.entities.Transaction;

import java.util.List;
import java.util.Map;

public class Rewards {
    private long customerId;

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public Map<Integer,Long> getRewardsByMonth() {
        return rewardsByMonth;
    }

    public void setRewardsByMonth(Map<Integer,Long> rewardsByMonth) {
        this.rewardsByMonth = rewardsByMonth;
    }

    public long getTotalRewards() {
        return totalRewards;
    }

    public void setTotalRewards(long totalRewards) {
        this.totalRewards = totalRewards;
    }

    private Map<Integer,Long> rewardsByMonth;
    private long totalRewards;
}
