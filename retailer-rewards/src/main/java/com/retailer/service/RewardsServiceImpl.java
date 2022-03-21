package com.retailer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.retailer.dao.RewardsRepository;
import com.retailer.entities.Rewards;

/**
 * 
 * @author Janak Patel
 */
@Service("rewardService")
@Transactional
public class RewardsServiceImpl implements RewardsService {
	private final Logger LOGGER = LoggerFactory.getLogger(RewardsServiceImpl.class);

	@Autowired
	private RewardsRepository rewardRepo;

	@Override
	public String getRewardsByCustomerId(String id) {
		LOGGER.info("Inside RewardsServiceImpl : getRewardsByCustomerId() with customer Id: " + id);

		List<Rewards> rewards = rewardRepo.findByCustomerId(id);
		if (rewards.size() > 0) {
			LOGGER.info("Inside RewardsServiceImpl : Fetched customer with customer Id: " + id);
		} else {
			LOGGER.info("Inside RewardsServiceImpl : No customer found with customer Id: " + id);
		}

		String reward = calculateRewardForOneCustomer(rewards);
		LOGGER.info("Inside RewardsServiceImpl : Reward Detail: " + reward);
		return reward;
	}

	@Override
	public List<String> getRewardsForCustomers() {
		LOGGER.info("Inside RewardsServiceImpl : getRewardsForCustomers()");
		List<Rewards> rewards = rewardRepo.findAll();
		List<String> rewardList = calculateRewardForAllCustomer(rewards);
		return rewardList;
	}

	private String calculateRewardForOneCustomer(List<Rewards> rewards) {
		LOGGER.info("Inside RewardsServiceImpl : calculateRewardForOneCustomer()");
		double currentReward = 0;
		String customerId = null;
		for (Rewards reward : rewards) {
			customerId = reward.getCustomerId();
			LOGGER.info("Inside RewardsServiceImpl: calculateRewardForOneCustomer: Cutomer Id: "+customerId);
			double tranAmount = reward.getTrnAmnt().doubleValue();
			if (tranAmount > 50 && tranAmount <= 100) { // reward point for amount > 50 but <= 100
				currentReward = currentReward + 1 * (tranAmount - 50);
			}
			if (tranAmount > 100) { // reward for amount > 100
				currentReward = currentReward + 2 * (tranAmount - 100);
			}
		}
		String message = "Customer Id: " + customerId + "\t" + "RewardAmount: $" + currentReward;
		LOGGER.info("Inside RewardsServiceImpl: calculateRewardForOneCustomer: message: "+message);
		return message;
	}

	private List<String> calculateRewardForAllCustomer(List<Rewards> rewards) {
		LOGGER.info("Inside RewardsServiceImpl : calculateRewardForAllCustomer()");
		List<String> messageList = new ArrayList<>();
		Map<String, Double> customerMap = new HashMap<>();
		double totalReward = 0;
		String customerId = null;

		for (Rewards reward : rewards) {
			double currentReward = 0;
			customerId = reward.getCustomerId();
			LOGGER.info("Inside RewardsServiceImpl: calculateRewardForAllCustomer(): Cutomer Id: "+customerId);
			double tranAmount = reward.getTrnAmnt().doubleValue();
			if (tranAmount > 50 && tranAmount <= 100) { // reward point for amount > 50 but <= 100
				currentReward = currentReward + 1 * (tranAmount - 50);
			}
			if (tranAmount > 100) { // reward for amount > 100
				currentReward = currentReward + 2 * (tranAmount - 100);
			}
			// add the reward in map for customer id
			if (customerMap.containsKey(customerId)) {
				double rewardAmt = customerMap.get(customerId);
				rewardAmt = rewardAmt + currentReward;
				customerMap.put(customerId, rewardAmt);
			} else {
				customerMap.put(customerId, currentReward);
			}

			totalReward = totalReward + currentReward;
		}
		for (String customer : customerMap.keySet()) {
			String message = "Customer Id: " + customer + ", " + "RewardAmount: $" + customerMap.get(customer);
			LOGGER.info("Inside RewardsServiceImpl: calculateRewardForOneCustomer: message: "+message);
			messageList.add(message);
		}
		String totalRewardMsg = "Total Reward Amount = $" + totalReward;
		LOGGER.info("Inside RewardsServiceImpl: calculateRewardForOneCustomer: totalRewardMsg: "+totalRewardMsg);
		messageList.add(totalRewardMsg);
		return messageList;
	}
}
