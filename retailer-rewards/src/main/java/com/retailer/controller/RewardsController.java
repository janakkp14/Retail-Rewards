package com.retailer.controller;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.retailer.service.RewardsService;

/**
 * 
 * @author Janak Patel
 */

@RestController
public class RewardsController {
	private final Logger LOGGER = LoggerFactory.getLogger(RewardsController.class);
	
	@Autowired
	private RewardsService rewardsService;

	@GetMapping("/customer/{id}")
	public String getRewardsForCustomer(@PathVariable("id") String id) {
		LOGGER.info("Inside RewardsController : getRewardsForCustomer() with customer Id: "+ id);
		return rewardsService.getRewardsByCustomerId(id);
	}
	
	@GetMapping("/customer")
	public List<String> getRewardsForAllCustomers() {
		LOGGER.info("Inside RewardsController : getRewardsForAllCustomers");
		return rewardsService.getRewardsForCustomers();
	}
}
