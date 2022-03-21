package com.retailer.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.retailer.entities.Rewards;

@Repository
public interface RewardsRepository extends JpaRepository<Rewards, Integer>{
	final Logger LOGGER = LoggerFactory.getLogger(RewardsRepository.class);
	List<Rewards> findByCustomerId(String id);

}
