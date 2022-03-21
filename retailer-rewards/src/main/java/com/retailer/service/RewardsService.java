package com.retailer.service;

import java.util.List;

public interface RewardsService {
	String getRewardsByCustomerId(String id);
	List<String> getRewardsForCustomers();
}
