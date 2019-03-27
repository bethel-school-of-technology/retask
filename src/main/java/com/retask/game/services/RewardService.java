package com.retask.game.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retask.game.model.Reward;
import com.retask.game.model.UserTask;
import com.retask.game.repository.RewardRepository;

@Service
public class RewardService {

	@Autowired
	private RewardRepository rewardRepository;
	
	/**
	 * gets the tasks by the username
	 * 
	 * @param username
	 * @return
	 */
	public List<Reward> getRewardsByUsername(String username) {

		List<Reward> rewards = rewardRepository.findByUsername(username);
				
		return rewards;
	}
	
	public boolean createRewardsForUsername(List<Reward> rewards) {
		
		for (Reward reward: rewards) {	
			rewardRepository.save(reward);
		}
		
		return true;
	}
	
}
